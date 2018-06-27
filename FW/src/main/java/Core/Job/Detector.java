package Core.Job;

import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Created by Administrator on 2017-08-12.
 */
public class Detector {
    private static Detector _item;
    public static Detector Instance(String p_strTestURL) {
        if (_item == null) {
            _item = new Detector(p_strTestURL);
        }
        return _item;
    }

    private static String _Url;
    public Detector(String p_strURL) {
        _Url = p_strURL;
    }
    private void keepTomcatAlive() throws NullPointerException {
        String s;
        String t = new String("tomcat7");
        boolean isTomcatAlive = false;
        java.io.BufferedReader in;
        System.setProperty("sun.net.client.defaultConnectTimeout", "8000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        try {
            URL url = new URL(_Url);
            URLConnection con = url.openConnection();
            in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
            con.setConnectTimeout(1000);
            con.setReadTimeout(4000);
            while ((s = in.readLine()) != null) {
                if (s.length() > 0) {
                    return;
                }
            }
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            Process p = Runtime.getRuntime().exec("ps -aux");
            in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
            while ((s = in.readLine()) != null) {
                if (s.startsWith(t)) {
                    isTomcatAlive = true;
                    break;
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isTomcatAlive) {
            System.out.println("<" + new Date() + "> Tomcat is alive but not response!");
            stopTomcat("/etc/init.d/tomcat6 stop");
        }

        startTomcat("/etc/init.d/tomcat6 start");
    }

    /**
     命令 "/etc/init.d/tomcat6 stop"
     */
    public void stopTomcat(String p_strCMD) {
        try {
            Process p = Runtime.getRuntime().exec(p_strCMD);
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
            String s;
            String t = "Stopping";
            boolean restart = false;
            while ((s = in.readLine()) != null) {
                if (s.indexOf(t) != -1) {
                    restart = true;
                    break;
                }
            }
            System.out.println("<" + new Date() + "> Tomcat is stop " + (restart ? "OK" : "ERROR"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    命令 "/etc/init.d/tomcat6 start"
    */
    public static void startTomcat(String p_strCMD) {
        try {
            Process p = Runtime.getRuntime().exec(p_strCMD);
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
            String s;
            String t = "Starting";
            boolean restart = false;
            while ((s = in.readLine()) != null) {
                if (s.indexOf(t) != -1) {
                    restart = true;
                    break;
                }
            }
            System.out.println("<" + new Date() + "> Tomcat is start " + (restart ? "OK" : "ERROR"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void debug(String msg) {
        System.out.println("Debug::: " + msg);
    }

    public static void main(String[] args) {
        while (true) {
            try {
                debug("Detect agin <" + new Date() + ">");
                Detector.Instance("http://localhost:8080/test.jsp").keepTomcatAlive();
                debug("Sleep...");
                Thread.sleep(30000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
