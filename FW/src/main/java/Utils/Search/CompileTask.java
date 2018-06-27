package Utils.Search;//package FW.Service;
//
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import org.apache.maven.plugin.logging.Log;
//
//import java.io.*;
//import java.util.concurrent.Callable;
//
///**
// * Created by Administrator on 2017-08-19.
// */
//public class CompileTask implements Callable<Void> {
//    private final File source;
//    private final File destination;
//    private final Log log;
//    private final boolean verbose;
//
//    /**
//     * @param source The source file.
//     * @param destination The destination file.
//     * @param log The logger.
//     * @param verbose If the output should be verbose.
//     */
//    public CompileTask(File source, File destination, Log log, boolean verbose) {
//        super();
//        this.source = source;
//        this.destination = destination;
//        this.log = log;
//        this.verbose = verbose;
//    }
//
//    /**
//     * Compile the source file. If the source file doesn't have the right
//     * extension, it is skipped.
//     *
//     * @return Debug output of the compile action.
//     * @throws Exception when anything goes wrong while compiling.
//     */
//    @Override
//    public Void call() throws Exception {
//        OutputStream out = null;
//        InputStream in = null;
//        try {
//            out = new FileOutputStream(destination);
//            in = new FileInputStream(source);
//            JasperCompileManager.compileReportToStream(in, out);
//            if (verbose) {
//                log.info("Compiling " + source.getName());
//            }
//        } catch (Exception e) {
//            cleanUpAndThrowError(destination, e);
//        } finally {
//            if (out != null) {
//                out.close();
//            }
//            if (in != null) {
//                in.close();
//            }
//        }
//        return null;
//    }
//
//    private void cleanUpAndThrowError(File out, Exception e) throws JRException {
//        log.error("Could not compile " + source.getName() + " because " + e.getMessage(), e);
//        if (out != null && out.exists()) {
//            out.delete();
//        }
//        throw new JRException("Could not compile " + source.getName(), e);
//    }
//}
