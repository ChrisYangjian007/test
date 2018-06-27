package Utils;

import Configs.PropertiesInfo;
import Extend.StringExtend;
import Models.Enum.WatermarkType;
import Models.ImageRectangle;
import org.im4java.core.*;
import org.im4java.process.ArrayListOutputConsumer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-14.
 * 需要安装GraphicsMagick-1.3.26
 */
public class ImageUtil {
    /// log4对象
//    private static Logger logger = Logger.getLogger(ImageUtil.class);
    private static ImageUtil item;
    public static ImageUtil Instance() {
        if (item == null) {
            item = new ImageUtil();
        }
        return item;
    }

    /**
     * 是否使用 GraphicsMagick
     */
    private boolean USE_GRAPHICS_MAGICK_PATH = Boolean.parseBoolean(PropertiesInfo.getKeyValue("UseGraphicsMagick"));

    /**
     * ImageMagick安装路径
     */
    private String IMAGE_MAGICK_PATH = PropertiesInfo.getKeyValue("ImageMagickPath");

    /**
     * GraphicsMagick 安装目录
     */
    private String GRAPHICS_MAGICK_PATH = PropertiesInfo.getKeyValue("GraphicsMagickPath");

    /**
     * 水印图片路径
     */
    private String watermarkImagePath = "watermark.png";

    /**
     * 水印图片
     */
    private Image watermarkImage = null;

    /**
     * 命令类型
     * @author hailin0@yeah.net
     * @createDate 2016年6月5日
     */
    private enum CommandType {
        convert("转换处理"), identify("图片信息"), compositecmd("图片合成");
        private String name;

        CommandType(String name) {
            this.name = name;
        }
    }

    /**
     * 获取 ImageCommand
     * @param command 命令类型
     */
    private ImageCommand getImageCommand(CommandType command) {
        ImageCommand cmd = null;
        switch (command) {
            case convert:
                cmd = new ConvertCmd(USE_GRAPHICS_MAGICK_PATH);
                break;
            case identify:
                cmd = new IdentifyCmd(USE_GRAPHICS_MAGICK_PATH);
                break;
            case compositecmd:
                cmd = new CompositeCmd(USE_GRAPHICS_MAGICK_PATH);
                break;
        }
        if (cmd != null && System.getProperty("os.name").toLowerCase().indexOf("windows") != -1) {
            cmd.setSearchPath(USE_GRAPHICS_MAGICK_PATH ? GRAPHICS_MAGICK_PATH : IMAGE_MAGICK_PATH);
        }
        return cmd;
    }

    /**
     * 获取图片信息
     * @param srcImagePath 图片路径
     * @return Map {height=, filelength=, directory=, width=, filename=}
     */
    public Map<String, Object> getImageInfo(String srcImagePath) {
        IMOperation op = new IMOperation();
        op.format("%w,%h,%d,%f,%b");
        op.addImage(srcImagePath);
        IdentifyCmd identifyCmd = (IdentifyCmd) getImageCommand(CommandType.identify);
        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
        identifyCmd.setOutputConsumer(output);
        try {
            identifyCmd.run(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> cmdOutput = output.getOutput();
        if (cmdOutput.size() != 1)
            return null;
        String line = cmdOutput.get(0);
        String[] arr = line.split(",");
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("width", Integer.parseInt(arr[0]));
        info.put("height", Integer.parseInt(arr[1]));
        info.put("directory", arr[2]);
        info.put("filename", arr[3]);
        info.put("filelength", Integer.parseInt(arr[4]));
        return info;
    }

    /**
     * 文字水印
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @param imageRectangle 文字内容等（不支持汉字）
     * @throws Exception
     */
    public void addTextWatermark(String srcImagePath, String destImagePath,  ImageRectangle imageRectangle) throws Exception {
        IMOperation op = new IMOperation();
        op.font("微软雅黑");
        // 文字方位-东南
        op.gravity("southeast");

        // 文字信息
        op.pointsize(18).fill("#BCBFC8").draw("text 10,10 " + imageRectangle.getContent());
        // 原图
        op.addImage(srcImagePath);
        // 目标
        op.addImage(createDirectory(destImagePath));
        ImageCommand cmd = getImageCommand(CommandType.convert);
        cmd.run(op);
    }

    /**
     * 图片水印
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @param imageRectangle 文字内容等
     * @throws Exception
     */
    public void addImgWatermark(String srcImagePath, String destImagePath, ImageRectangle imageRectangle) throws Exception {
        // 原始图片信息
        BufferedImage buffimg = ImageIO.read(new File(srcImagePath));
        int w = buffimg.getWidth();
        int h = buffimg.getHeight();
        if (!StringExtend.Empty(imageRectangle.getContent())) {
            watermarkImagePath = imageRectangle.getContent();
        }
        watermarkImage = ImageIO.read(new File(watermarkImagePath));
        IMOperation op = new IMOperation();
        // 水印图片位置
        op.geometry(watermarkImage.getWidth(null), watermarkImage.getHeight(null), w - watermarkImage.getWidth(null) - 10, h - watermarkImage.getHeight(null) - 10);
        // 水印透明度
        op.dissolve(imageRectangle.getdissolve());

        // 水印
        op.addImage(watermarkImagePath);
        // 原图
        op.addImage(srcImagePath);
        // 目标
        op.addImage(createDirectory(destImagePath));
        ImageCommand cmd = getImageCommand(CommandType.compositecmd);
        cmd.run(op);
    }
    /**
     * 压缩图片
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @throws Exception
     */
    public void resize(String srcImagePath, String destImagePath, ImageRectangle imageRectangle) throws Exception {
        // 按照原有形状压缩（横图、竖图）
        BufferedImage buffimg = ImageIO.read(new File(srcImagePath));
        int w = buffimg.getWidth();
        int h = buffimg.getHeight();
        if ((w > h && imageRectangle.getWidth() < imageRectangle.getHeight()) || (w < h && imageRectangle.getWidth() > imageRectangle.getHeight())) {
            int temp = imageRectangle.getWidth();
            imageRectangle.setWidth(imageRectangle.getHeight());
            imageRectangle.setHeight(temp);
        }
        // 是否压缩
        if (w < imageRectangle.getWidth() || h < imageRectangle.getHeight()) {
            // 不压缩-是否加水印
            if (imageRectangle.getWatermarkType()== WatermarkType.Image) {
                // 不压缩，加水印
                addImgWatermark(srcImagePath, destImagePath, imageRectangle);
            }
            return;
        }
        // 压缩-是否加水印
        if (imageRectangle.getWatermarkType()== WatermarkType.Image) {
            // 压缩-加水印比例
            double cropRatio = 0f;
            if ((imageRectangle.getWidth() + 0.0) / (w + 0.0) > (imageRectangle.getHeight() + 0.0) / (h + 0.0)) {
                cropRatio = (imageRectangle.getHeight() + 0.0) / (h + 0.0);
            } else {
                cropRatio = (imageRectangle.getWidth() + 0.0) / (w + 0.0);
            }
            IMOperation op = new IMOperation();
            ImageCommand cmd = getImageCommand(CommandType.compositecmd);
            op.geometry(watermarkImage.getWidth(null), watermarkImage.getHeight(null),
                    (int) (w * cropRatio) - watermarkImage.getWidth(null) - 10,
                    (int) (h * cropRatio) - watermarkImage.getHeight(null) - 10);
            op.addImage(watermarkImagePath);
            op.addImage(srcImagePath);
            op.quality(100d);
            op.resize(imageRectangle.getWidth(), imageRectangle.getHeight());
            op.addImage(createDirectory(destImagePath));
            cmd.run(op);
            return;
        }

        // 压缩-不加水印
        ImageCommand cmd = getImageCommand(CommandType.convert);
        IMOperation op = new IMOperation();
        op.addImage(srcImagePath);
        op.quality(100d);
        op.resize(imageRectangle.getWidth(), imageRectangle.getHeight());
        op.addImage(createDirectory(destImagePath));
        cmd.run(op);
    }

    /**
     * 去除Exif信息，可减小文件大小
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @throws Exception
     */
    public void removeProfile(String srcImagePath, String destImagePath) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcImagePath);
        op.profile("*");
        op.addImage(createDirectory(destImagePath));
        ImageCommand cmd = getImageCommand(CommandType.convert);
        cmd.run(op);
    }

    /**
     * 等比缩放图片（如果width为空，则按height缩放; 如果height为空，则按width缩放）
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @param imageRectangle 参数
     * @throws Exception
     */
    public void scaleResize(String srcImagePath, String destImagePath, ImageRectangle imageRectangle) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcImagePath);
        op.sample(imageRectangle.getWidth(), imageRectangle.getHeight());
        op.addImage(createDirectory(destImagePath));
        ImageCommand cmd = getImageCommand(CommandType.convert);
        cmd.run(op);
    }

    /**
     * 从原图中裁剪出新图
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @throws Exception
     */
    public void crop(String srcImagePath, String destImagePath, ImageRectangle imageRectangle) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcImagePath);
        op.crop(imageRectangle.getWidth(), imageRectangle.getHeight(), imageRectangle.getX(), imageRectangle.getY());
        op.addImage(createDirectory(destImagePath));
        ImageCommand cmd = getImageCommand(CommandType.convert);
        cmd.run(op);
    }

    /**
     * 旋转图片
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @param angle 旋转的角度
     * @throws Exception
     */
    public void rotate(String srcImagePath, String destImagePath, Double angle) throws Exception {
        File sourceFile = new File(srcImagePath);
        if (!sourceFile.exists() || !sourceFile.canRead() || !sourceFile.isFile()) {
            return;
        }
        BufferedImage buffimg = ImageIO.read(sourceFile);
        int w = buffimg.getWidth();
        int h = buffimg.getHeight();
        // 目标图片
        // if (w > h) { //如果宽度不大于高度则旋转过后图片会变大
        ImageCommand cmd = getImageCommand(CommandType.convert);
        IMOperation operation = new IMOperation();
        operation.addImage(srcImagePath);
        operation.rotate(angle);
        operation.addImage(destImagePath);
        cmd.run(operation);
        // }
    }

    /**
     * 创建目录
     * @param path
     * @return path
     */
    private String createDirectory(String path) {
        File file = new File(path);
        if (!file.exists())
            file.getParentFile().mkdirs();
        return path;
    }

//    /**
//     * 用Format对应格式中ImageIO默认参数把IMAGE打包成BYTE[]
//     *
//     * @param image
//     * @return
//     */
//    private byte[] bufferedImageTobytes(BufferedImage image, String format) {
//        System.out.println(format + "格式开始打包" + getCurrentTime());
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(image, format, out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(format + "格式完成打包-----" + getCurrentTime()
//                + "----lenth------" + out.toByteArray().length);
//        return out.toByteArray();
//    }
//
//    /**
//     *
//     * 自己设置压缩质量来把图片压缩成byte[]
//     *
//     * @param image
//     *            压缩源图片
//     * @param quality
//     *            压缩质量，在0-1之间，
//     * @return 返回的字节数组
//     */
//    private byte[] bufferedImageTobytes(BufferedImage image, float quality) {
//        System.out.println("jpeg" + quality + "质量开始打包" + getCurrentTime());
//        // 如果图片空，返回空
//        if (image == null) {
//            return null;
//        }
//        // 得到指定Format图片的writer
//        Iterator<ImageWriter> iter = ImageIO
//                .getImageWritersByFormatName("jpeg");// 得到迭代器
//        ImageWriter writer = (ImageWriter) iter.next(); // 得到writer
//
//        // 得到指定writer的输出参数设置(ImageWriteParam )
//        ImageWriteParam iwp = writer.getDefaultWriteParam();
//        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
//        iwp.setCompressionQuality(quality); // 设置压缩质量参数
//
//        iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
//
//        ColorModel colorModel = ColorModel.getRGBdefault();
//        // 指定压缩时使用的色彩模式
//        iwp.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,
//                colorModel.createCompatibleSampleModel(16, 16)));
//
//        // 开始打包图片，写入byte[]
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
//        IIOImage iIamge = new IIOImage(image, null, null);
//        try {
//            // 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
//            // 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
//            writer.setOutput(ImageIO
//                    .createImageOutputStream(byteArrayOutputStream));
//            writer.write(null, iIamge, iwp);
//        } catch (IOException e) {
//            System.out.println("write errro");
//            e.printStackTrace();
//        }
//        System.out.println("jpeg" + quality + "质量完成打包-----" + getCurrentTime()
//                + "----lenth----" + byteArrayOutputStream.toByteArray().length);
//        return byteArrayOutputStream.toByteArray();
//    }
//
//    /**
//     * 自己定义格式，得到当前系统时间
//     *
//     * @return
//     */
//    private String getCurrentTime() {
//        Calendar c = new GregorianCalendar();
//        int hour = c.get(Calendar.HOUR_OF_DAY);
//        int min = c.get(Calendar.MINUTE);
//        int second = c.get(Calendar.SECOND);
//        int millsecond = c.get(Calendar.MILLISECOND);
//        String time = hour + "点" + min + "分" + second + "秒" + millsecond;
//        return time;
//    }
//
//    /**
//     * 通过 com.sun.image.codec.jpeg.JPEGCodec提供的编码器来实现图像压缩
//     *
//     * @param image
//     * @param quality
//     * @return
//     */
//    private byte[] newCompressImage(BufferedImage image, float quality) {
//        // 如果图片空，返回空
//        if (image == null) {
//            return null;
//        }
//        // 开始开始，写入byte[]
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
//        // 设置压缩参数
//        JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(image);
//        param.setQuality(quality, false);
//        // 设置编码器
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(
//                byteArrayOutputStream, param);
//        System.out.println("newCompressive" + quality + "质量开始打包"
//                + getCurrentTime());
//        try {
//            encoder.encode(image);
//        } catch (Exception ef) {
//            ef.printStackTrace();
//        }
//        System.out.println("newCompressive" + quality + "质量打包完成"
//                + getCurrentTime() + "----lenth----"
//                + byteArrayOutputStream.toByteArray().length);
//        return byteArrayOutputStream.toByteArray();
//
//    }
//
//    /**
//     * 测试把图片先压缩成JPEG，再用JPEG压缩成GIF
//     */
//    public byte[] giftest(byte[] imagedata) {
//        System.out.println("giftest开始打包" + getCurrentTime());
//        BufferedImage image = null;
//        ByteArrayInputStream input = new ByteArrayInputStream(imagedata);
//        // 得到解码器
//        JPEGImageDecoder decoder = (JPEGImageDecoder) JPEGCodec
//                .createJPEGDecoder(input);
//        // 把JPEG 数据流解压缩
//        try {
//            image = ((com.sun.image.codec.jpeg.JPEGImageDecoder) decoder)
//                    .decodeAsBufferedImage();
//        } catch (Exception ef) {
//            ef.printStackTrace();
//        }
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(image, "gif", out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("giftest开始打包" + getCurrentTime() + "----lenth----"
//                + out.toByteArray().length);
//        return out.toByteArray();
//    }
//    /// <summary>
//    /// 生成不同比例的图片
//    /// </summary>
//    /// <param name="p_strImagePath">图片地址</param>
//    /// <param name="p_strThumbnailPath">存放地址</param>
//    /// <param name="p_strFileDimension">图片尺寸比例</param>
//    /// <param name="p_hasPara">IsWaterMark,FontName,FontColor,FontPlace,FontSize,Font</param>
//    public int MakeThumbnail(String p_strImagePath, String p_strThumbnailPath, ImageRectangle p_ImageRectangle) throws IOException {
//        BufferedImage m_Image = null;
//        try {
//            m_Image = ImageIO.read(new File(p_strImagePath)); // 读入文件
//        } catch (Exception ex) {
//            logger.error("MakeThumbnail处理中找不到文件！" + ex.getMessage());
//            return ErrorInfo.SystemErrorFailure.getCode();
//        }
//        int m_intWidth = m_Image.getWidth();       //源图片宽
//        int m_intHeight = m_Image.getHeight();      //源图片高
//        int m_intTemp = 0;
//        if (p_ImageRectangle.getThumbnailType() == ThumbnailType.Proportion)//比例
//        {
//            if (p_ImageRectangle.getHeight() >= m_intHeight && p_ImageRectangle.getWidth() >= m_intWidth) {
//                p_ImageRectangle.setWidth(m_intWidth);
//                p_ImageRectangle.setHeight(m_intHeight);
//            } else {
//                m_intTemp = (p_ImageRectangle.getHeight() * m_intWidth) / m_intHeight;
//                if (m_intTemp >= p_ImageRectangle.getWidth()) {
//                    p_ImageRectangle.setHeight((p_ImageRectangle.getWidth() * m_intHeight) / m_intWidth);
//                } else {
//                    p_ImageRectangle.setWidth(m_intTemp);
//                    p_ImageRectangle.setHeight((m_intTemp * m_intHeight) / m_intWidth);
//                }
//            }
//        }
//        else if (p_ImageRectangle.getThumbnailType() == ThumbnailType.Fixed)//固定
//        {
//            if (p_ImageRectangle.getHeight() >= m_intHeight || p_ImageRectangle.getWidth() >= m_intWidth) {
//                p_ImageRectangle.setY(0);
//                p_ImageRectangle.setX(0);
//            } else {
//                p_ImageRectangle.setY((m_intHeight - p_ImageRectangle.getHeight()) > 0 ?  (int)((m_intHeight - p_ImageRectangle.getHeight()) / 2) : 0);
//                p_ImageRectangle.setX((m_intWidth - p_ImageRectangle.getWidth()) > 0 ? (int)((m_intWidth - p_ImageRectangle.getWidth()) / 2) : 0);
//            }
//        }
//        else {
//            if (p_ImageRectangle.getHeight() >= m_intHeight && p_ImageRectangle.getWidth() >= m_intWidth) {
//                p_ImageRectangle.setWidth(m_intWidth);
//                p_ImageRectangle.setHeight(m_intHeight);
//            } else {
//                if ((p_ImageRectangle.getHeight() / m_intHeight) < (p_ImageRectangle.getWidth() / m_intWidth)) {
//                    m_intTemp = (p_ImageRectangle.getHeight() * m_intWidth) / m_intHeight;
//                    p_ImageRectangle.setHeight((m_intTemp * m_intHeight) / m_intWidth);
//                    p_ImageRectangle.setX(m_intWidth - ((p_ImageRectangle.getWidth() * m_intHeight) / p_ImageRectangle.getHeight()));
//                    m_intWidth = m_intWidth - p_ImageRectangle.getX();
//                    p_ImageRectangle.setX(p_ImageRectangle.getX() / 2);
//                } else {
//                    m_intTemp = (p_ImageRectangle.getWidth() * m_intHeight) / m_intWidth;
//                    p_ImageRectangle.setHeight(m_intTemp);
//                    p_ImageRectangle.setY(m_intHeight - ((p_ImageRectangle.getHeight() * m_intWidth) / p_ImageRectangle.getWidth()));
//                    m_intHeight = m_intHeight - p_ImageRectangle.getY();
//                    p_ImageRectangle.setY(p_ImageRectangle.getY() / 2);
//                }
//            }
//        }
//        Image image = m_Image.getScaledInstance(m_intWidth, m_intHeight, Image.SCALE_DEFAULT);
//        BufferedImage tag = new BufferedImage(m_intWidth, m_intHeight,BufferedImage.TYPE_INT_RGB);
//        Graphics g = tag.getGraphics();
////        to = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
//        g.drawImage(image, 0, 0, null); // 绘制缩小后的图
//        g.dispose();
//        File m_File=new File(p_strThumbnailPath);
//        File m_File1=new File(p_strThumbnailPath+"test.png");
//        if (!m_File.exists())
//            m_File.mkdir();
//        if(m_File1.exists())
//            m_File1.delete();
//        if (p_ImageRectangle.getWidth() > 100)
//            ImageIO.write(tag, "JPEG", m_File1);// 输出到文件流
//        else
//            ImageIO.write(tag, "PNG", m_File1);// 输出到文件流
//
////        //新建一个bmp图片
////        Bitmap m_Bitmap = new Bitmap(p_ImageRectangle.Width, p_ImageRectangle.Height);
////        //新建一个画板
////        Graphics m_Graphics = Graphics.FromImage(m_Bitmap);
////        //下面这个也设成高质量
////        m_Graphics.CompositingQuality = System.Drawing.Drawing2D.CompositingQuality.HighQuality;
////        //设置高质量,低速度呈现平滑程度
////        m_Graphics.SmoothingMode = SmoothingMode.HighQuality;
////        //清空画布并以透明背景色填充
////        m_Graphics.Clear(System.Drawing.Color.Transparent);
////        m_Graphics.InterpolationMode = (p_ImageRectangle.ThumbnailType == ThumbnailType.Proportion || p_ImageRectangle.ThumbnailType == ThumbnailType.AutoFixed) ?
////                InterpolationMode.HighQualityBicubic : InterpolationMode.HighQualityBilinear;//设置高质量插值法
////        //在指定位置并且按指定大小绘制原图片的指定部分
////        m_Graphics.DrawImage(m_Image, new Rectangle(0, 0, p_ImageRectangle.Width, p_ImageRectangle.Height), new Rectangle(p_ImageRectangle.X, p_ImageRectangle.Y, m_intWidth, m_intHeight), GraphicsUnit.Pixel);
////        try
////        {
////            if (!Directory.Exists(p_strThumbnailPath.Substring(0, p_strThumbnailPath.LastIndexOf("\\")))) Directory.CreateDirectory(p_strThumbnailPath.Substring(0, p_strThumbnailPath.LastIndexOf("\\")));
////            //以jpg格式保存缩略图
////            if (p_ImageRectangle.Width > 100)
////                m_Bitmap.Save(p_strThumbnailPath, ImageFormat.Jpeg);
////            else
////                m_Bitmap.Save(p_strThumbnailPath, ImageFormat.Png);
////            return 1;
////        }
////        catch (Exception ex)
////        {
////            logger.Error("MakeThumbnail处理中出错！" + ex.Message);
////            return (int)ErrorInfo.SystemErrorFailure;
////            throw ex;
////        }
////        finally
////        {
////            m_Image.Dispose();
////            m_Bitmap.Dispose();
////            m_Graphics.Dispose();
////        }
//        return 1;
//    }
//    public void cropImage(String srcPath,String toPath,
//                          int x,int y,int width,int height,
//                          String readImageFormat,String writeImageFormat) throws IOException{
//        FileInputStream fis = null ;
//        ImageInputStream iis =null ;
//        try{
//            //读取图片文件
//            fis = new FileInputStream(srcPath);
//            Iterator it = ImageIO.getImageReadersByFormatName(readImageFormat);
//            ImageReader reader = (ImageReader) it.next();
//            //获取图片流
//            iis = ImageIO.createImageInputStream(fis);
//            reader.setInput(iis,true) ;
//            ImageReadParam param = reader.getDefaultReadParam();
//            //定义一个矩形
//            Rectangle rect = new Rectangle(x, y, width, height);
//            //提供一个 BufferedImage，将其用作解码像素数据的目标。
//            param.setSourceRegion(rect);
//            BufferedImage bi = reader.read(0,param);
//            //保存新图片
//            ImageIO.write(bi, writeImageFormat, new File(toPath));
//        }finally{
//            if(fis!=null)
//                fis.close();
//            if(iis!=null)
//                iis.close();
//        }
//    }
//
//    /**
//     * 按倍率缩小图片
//     * @param srcImagePath 读取图片路径
//     * @param toImagePath 写入图片路径
//     * @param widthRatio    宽度缩小比例
//     * @param heightRatio    高度缩小比例
//     * @throws IOException
//     */
//    public void reduceImageByRatio(String srcImagePath,String toImagePath,int widthRatio,int heightRatio) throws IOException{
//        FileOutputStream out = null;
//        try{
//            //读入文件
//            File file = new File(srcImagePath);
//            // 构造Image对象
//            BufferedImage src = javax.imageio.ImageIO.read(file);
//            int width = src.getWidth();
//            int height = src.getHeight();
//            // 缩小边长
//            BufferedImage tag = new BufferedImage(width / widthRatio, height / heightRatio, BufferedImage.TYPE_INT_RGB);
//            // 绘制 缩小  后的图片
//            tag.getGraphics().drawImage(src, 0, 0, width / widthRatio, height / heightRatio, null);
//            File m_File=new File(toImagePath);
//            ImageIO.write(tag, "JPG", m_File);// 输出到文件流
////            out = new FileOutputStream(toImagePath);
////            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
////            encoder.encode(tag);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(out != null){
//                out.close();
//            }
//        }
//    }
//
//    /**
//     * 长高等比例缩小图片
//     * @param srcImagePath 读取图片路径
//     * @param toImagePath 写入图片路径
//     * @param ratio 缩小比例
//     * @throws IOException
//     */
//    public void reduceImageEqualProportion(String srcImagePath,String toImagePath,int ratio) throws IOException{
//        FileOutputStream out = null;
//        try{
//            //读入文件
//            File file = new File(srcImagePath);
//            // 构造Image对象
//            BufferedImage src = javax.imageio.ImageIO.read(file);
//            int width = src.getWidth();
//            int height = src.getHeight();
//            // 缩小边长
//            BufferedImage tag = new BufferedImage(width / ratio, height / ratio, BufferedImage.TYPE_INT_RGB);
//            // 绘制 缩小  后的图片
//            tag.getGraphics().drawImage(src, 0, 0, width / ratio, height / ratio, null);
//            out = new FileOutputStream(toImagePath);
//
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(out != null){
//                out.close();
//            }
//        }
//    }
//
//    /**
//     * 按倍率放大图片
//     * @param srcImagePath 读取图形路径
//     * @param toImagePath 写入入行路径
//     * @param widthRatio    宽度放大比例
//     * @param heightRatio 高度放大比例
//     * @throws IOException
//     */
//    public void enlargementImageByRatio(String srcImagePath,String toImagePath,int widthRatio,int heightRatio) throws IOException{
//        FileOutputStream out = null;
//        try{
//            //读入文件
//            File file = new File(srcImagePath);
//            // 构造Image对象
//            BufferedImage src = javax.imageio.ImageIO.read(file);
//            int width = src.getWidth();
//            int height = src.getHeight();
//            // 放大边长
//            BufferedImage tag = new BufferedImage(width * widthRatio, height * heightRatio, BufferedImage.TYPE_INT_RGB);
//            //绘制放大后的图片
//            tag.getGraphics().drawImage(src, 0, 0, width * widthRatio, height * heightRatio, null);
//            out = new FileOutputStream(toImagePath);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(out != null){
//                out.close();
//            }
//        }
//    }
//
//
//    /**
//     * 长高等比例放大图片
//     * @param srcImagePath 读取图形路径
//     * @param toImagePath 写入入行路径
//     * @param ratio 放大比例
//     * @throws IOException
//     */
//    public void enlargementImageEqualProportion(String srcImagePath,String toImagePath,int ratio) throws IOException{
//        FileOutputStream out = null;
//        try{
//            //读入文件
//            File file = new File(srcImagePath);
//            // 构造Image对象
//            BufferedImage src = javax.imageio.ImageIO.read(file);
//            int width = src.getWidth();
//            int height = src.getHeight();
//            // 放大边长
//            BufferedImage tag = new BufferedImage(width * ratio, height * ratio, BufferedImage.TYPE_INT_RGB);
//            //绘制放大后的图片
//            tag.getGraphics().drawImage(src, 0, 0, width * ratio, height * ratio, null);
//            out = new FileOutputStream(toImagePath);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(out != null){
//                out.close();
//            }
//        }
//    }
//
//    /**
//     * 重置图形的边长大小
//     * @param srcImagePath
//     * @param toImagePath
//     * @param width
//     * @param height
//     * @throws IOException
//     */
//    public void resizeImage(String srcImagePath,String toImagePath,int width,int height) throws IOException{
//        FileOutputStream out = null;
//        try{
//            //读入文件
//            File file = new File(srcImagePath);
//            // 构造Image对象
//            BufferedImage src = javax.imageio.ImageIO.read(file);
//            // 放大边长
//            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            //绘制放大后的图片
//            tag.getGraphics().drawImage(src, 0, 0, width, height, null);
//            out = new FileOutputStream(toImagePath);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(out != null){
//                out.close();
//            }
//        }
//    }
//
//    /**
//     * 横向拼接图片（两张）
//     * @param firstSrcImagePath 第一张图片的路径
//     * @param secondSrcImagePath    第二张图片的路径
//     * @param imageFormat   拼接生成图片的格式
//     * @param toPath    拼接生成图片的路径
//     */
//    public void joinImagesHorizontal(String firstSrcImagePath, String secondSrcImagePath,String imageFormat, String toPath){
//        try {
//            //读取第一张图片
//            File  fileOne  =  new  File(firstSrcImagePath);
//            BufferedImage  imageOne = ImageIO.read(fileOne);
//            int  width  =  imageOne.getWidth();//图片宽度
//            int  height  =  imageOne.getHeight();//图片高度
//            //从图片中读取RGB
//            int[]  imageArrayOne  =  new  int[width*height];
//            imageArrayOne  =  imageOne.getRGB(0,0,width,height,imageArrayOne,0,width);
//
//            //对第二张图片做相同的处理
//            File  fileTwo  =  new  File(secondSrcImagePath);
//            BufferedImage  imageTwo  =  ImageIO.read(fileTwo);
//            int width2 = imageTwo.getWidth();
//            int height2 = imageTwo.getHeight();
//            int[]   ImageArrayTwo  =  new  int[width2*height2];
//            ImageArrayTwo  =  imageTwo.getRGB(0,0,width,height,ImageArrayTwo,0,width);
//            //ImageArrayTwo  =  imageTwo.getRGB(0,0,width2,height2,ImageArrayTwo,0,width2);
//
//            //生成新图片
//            //int height3 = (height>height2 || height==height2)?height:height2;
//            BufferedImage  imageNew  =  new  BufferedImage(width*2,height,BufferedImage.TYPE_INT_RGB);
//            //BufferedImage  imageNew  =  new  BufferedImage(width+width2,height3,BufferedImage.TYPE_INT_RGB);
//            imageNew.setRGB(0,0,width,height,imageArrayOne,0,width);//设置左半部分的RGB
//            imageNew.setRGB(width,0,width,height,ImageArrayTwo,0,width);//设置右半部分的RGB
//            //imageNew.setRGB(width,0,width2,height2,ImageArrayTwo,0,width2);//设置右半部分的RGB
//
//            File  outFile  =  new  File(toPath);
//            ImageIO.write(imageNew,  imageFormat,  outFile);//写图片
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 横向拼接一组（多张）图像
//     * @param pics  将要拼接的图像
//     * @param type 图像写入格式
//     * @param dst_pic 图像写入路径
//     * @return
//     */
//    public boolean joinImageListHorizontal(String[] pics, String type, String dst_pic) {
//        try {
//            int len = pics.length;
//            if (len < 1) {
//                System.out.println("pics len < 1");
//                return false;
//            }
//            File[] src = new File[len];
//            BufferedImage[] images = new BufferedImage[len];
//            int[][] imageArrays = new int[len][];
//            for (int i = 0; i < len; i++) {
//                src[i] = new File(pics[i]);
//                images[i] = ImageIO.read(src[i]);
//                int width = images[i].getWidth();
//                int height = images[i].getHeight();
//                imageArrays[i] = new int[width * height];// 从图片中读取RGB
//                imageArrays[i] = images[i].getRGB(0, 0, width, height,  imageArrays[i], 0, width);
//            }
//
//            int dst_width = 0;
//            int dst_height = images[0].getHeight();
//            for (int i = 0; i < images.length; i++) {
//                dst_height = dst_height > images[i].getHeight() ? dst_height : images[i].getHeight();
//                dst_width += images[i].getWidth();
//            }
//            //System.out.println(dst_width);
//            //System.out.println(dst_height);
//            if (dst_height < 1) {
//                System.out.println("dst_height < 1");
//                return false;
//            }
//            /*
//             * 生成新图片
//             */
//            BufferedImage ImageNew = new BufferedImage(dst_width, dst_height,  BufferedImage.TYPE_INT_RGB);
//            int width_i = 0;
//            for (int i = 0; i < images.length; i++) {
//                ImageNew.setRGB(width_i, 0, images[i].getWidth(), dst_height,  imageArrays[i], 0, images[i].getWidth());
//                width_i += images[i].getWidth();
//            }
//            File outFile = new File(dst_pic);
//            ImageIO.write(ImageNew, type, outFile);// 写图片
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 纵向拼接图片（两张）
//     * @param firstSrcImagePath 读取的第一张图片
//     * @param secondSrcImagePath    读取的第二张图片
//     * @param imageFormat 图片写入格式
//     * @param toPath    图片写入路径
//     */
//    public void joinImagesVertical(String firstSrcImagePath, String secondSrcImagePath,String imageFormat, String toPath){
//        try {
//            //读取第一张图片
//            File  fileOne  =  new  File(firstSrcImagePath);
//            BufferedImage  imageOne = ImageIO.read(fileOne);
//            int  width  =  imageOne.getWidth();//图片宽度
//            int  height  =  imageOne.getHeight();//图片高度
//            //从图片中读取RGB
//            int[]  imageArrayOne  =  new  int[width*height];
//            imageArrayOne  =  imageOne.getRGB(0,0,width,height,imageArrayOne,0,width);
//
//            //对第二张图片做相同的处理
//            File  fileTwo  =  new  File(secondSrcImagePath);
//            BufferedImage  imageTwo  =  ImageIO.read(fileTwo);
//            int width2 = imageTwo.getWidth();
//            int height2 = imageTwo.getHeight();
//            int[]   ImageArrayTwo  =  new  int[width2*height2];
//            ImageArrayTwo  =  imageTwo.getRGB(0,0,width,height,ImageArrayTwo,0,width);
//            //ImageArrayTwo  =  imageTwo.getRGB(0,0,width2,height2,ImageArrayTwo,0,width2);
//
//            //生成新图片
//            //int width3 = (width>width2 || width==width2)?width:width2;
//            BufferedImage  imageNew  =  new  BufferedImage(width,height*2,BufferedImage.TYPE_INT_RGB);
//            //BufferedImage  imageNew  =  new  BufferedImage(width3,height+height2,BufferedImage.TYPE_INT_RGB);
//            imageNew.setRGB(0,0,width,height,imageArrayOne,0,width);//设置上半部分的RGB
//            imageNew.setRGB(0,height,width,height,ImageArrayTwo,0,width);//设置下半部分的RGB
//            //imageNew.setRGB(0,height,width2,height2,ImageArrayTwo,0,width2);//设置下半部分的RGB
//
//            File  outFile  =  new  File(toPath);
//            ImageIO.write(imageNew,  imageFormat,  outFile);//写图片
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 纵向拼接一组（多张）图像
//     * @param pics      将要拼接的图像数组
//     * @param type  写入图像类型
//     * @param dst_pic   写入图像路径
//     * @return
//     */
//    public boolean joinImageListVertical(String[] pics, String type, String dst_pic) {
//        try {
//            int len = pics.length;
//            if (len < 1) {
//                System.out.println("pics len < 1");
//                return false;
//            }
//            File[] src = new File[len];
//            BufferedImage[] images = new BufferedImage[len];
//            int[][] imageArrays = new int[len][];
//            for (int i = 0; i < len; i++) {
//                //System.out.println(i);
//                src[i] = new File(pics[i]);
//                images[i] = ImageIO.read(src[i]);
//                int width = images[i].getWidth();
//                int height = images[i].getHeight();
//                imageArrays[i] = new int[width * height];// 从图片中读取RGB
//                imageArrays[i] = images[i].getRGB(0, 0, width, height,  imageArrays[i], 0, width);
//            }
//
//            int dst_height = 0;
//            int dst_width = images[0].getWidth();
//            for (int i = 0; i < images.length; i++) {
//                dst_width = dst_width > images[i].getWidth() ? dst_width : images[i].getWidth();
//                dst_height += images[i].getHeight();
//            }
//            //System.out.println(dst_width);
//            //System.out.println(dst_height);
//            if (dst_height < 1) {
//                System.out.println("dst_height < 1");
//                return false;
//            }
//            /*
//             * 生成新图片
//             */
//            BufferedImage ImageNew = new BufferedImage(dst_width, dst_height,  BufferedImage.TYPE_INT_RGB);
//            int height_i = 0;
//            for (int i = 0; i < images.length; i++) {
//                ImageNew.setRGB(0, height_i, dst_width, images[i].getHeight(),  imageArrays[i], 0, dst_width);
//                height_i += images[i].getHeight();
//            }
//            File outFile = new File(dst_pic);
//            ImageIO.write(ImageNew, type, outFile);// 写图片
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 合并图片(按指定初始x、y坐标将附加图片贴到底图之上)
//     * @param negativeImagePath 背景图片路径
//     * @param additionImagePath 附加图片路径
//     * @param x 附加图片的起始点x坐标
//     * @param y  附加图片的起始点y坐标
//     * @param toPath 图片写入路径
//     * @throws IOException
//     */
//    public void mergeBothImage(String negativeImagePath,String additionImagePath,int x,int y,String toPath ) throws IOException{
//        InputStream is= null;
//        InputStream is2= null;
//        OutputStream os = null;
//        try{
//            is=new FileInputStream(negativeImagePath);
//            is2=new FileInputStream(additionImagePath);
//            BufferedImage image=ImageIO.read(is);
//            BufferedImage image2=ImageIO.read(is2);
//            Graphics g=image.getGraphics();
//            g.drawImage(image2,x,y,null);
//            os = new FileOutputStream(toPath);
//            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
//            enc.encode(image);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(os != null){
//                os.close();
//            }
//            if(is2 != null){
//                is2.close();
//            }
//            if(is != null){
//                is.close();
//            }
//        }
//    }
//
////    /**
////     * 将一组图片一次性附加合并到底图上
////     * @param negativeImagePath     源图像（底图）路径
////     * @param additionImageList 附加图像信息列表
////     * @param imageFormat   图像写入格式
////     * @param toPath    图像写入路径
////     * @throws IOException
////     */
////    public void mergeImageList(String negativeImagePath,List additionImageList,String imageFormat, String toPath) throws IOException{
////        InputStream is= null;
////        InputStream is2= null;
////        OutputStream os = null;
////        try{
////            is=new FileInputStream(negativeImagePath);
////            BufferedImage image=ImageIO.read(is);
////            //Graphics g=image.getGraphics();
////            Graphics2D g = image.createGraphics();;
////            BufferedImage image2 = null;
////            if(additionImageList != null){
////                for(int i=0;i<additionImageList.size();i++){
////                    //解析附加图片信息：x坐标、 y坐标、 additionImagePath附加图片路径
////                    //图片信息存储在一个数组中
////                    String[] additionImageInfo = (String[]) additionImageList.get(i);
////                    int x = Integer.parseInt(additionImageInfo[0]);
////                    int y = Integer.parseInt(additionImageInfo[1]);
////                    String additionImagePath = additionImageInfo[2];
////                    //读取文件输入流，并合并图片
////                    is2 = new FileInputStream(additionImagePath);
////                    //System.out.println(x+"  :  "+y+"  :  "+additionImagePath);
////                    image2 = ImageIO.read(is2);
////                    g.drawImage(image2,x,y,null);
////                }
////            }
////            os = new FileOutputStream(toPath);
////            ImageIO.write(image,  imageFormat,  os);//写图片
////            //JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
////            //enc.encode(image);
////        }catch(Exception e){
////            e.printStackTrace();
////        }finally{
////            if(os != null){
////                os.close();
////            }
////            if(is2 != null){
////                is2.close();
////            }
////            if(is != null){
////                is.close();
////            }
////        }
////    }
//
//    /**
//     * 将附加图片合并到底图的左上角
//     * @param negativeImagePath 底图路径
//     * @param additionImagePath 附加图片路径
//     * @param toPath    合成图片写入路径
//     * @throws IOException
//     */
//    public void mergeBothImageTopleftcorner(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
//        InputStream is= null;
//        InputStream is2= null;
//        OutputStream os = null;
//        try{
//            is=new FileInputStream(negativeImagePath);
//            is2=new FileInputStream(additionImagePath);
//            BufferedImage image=ImageIO.read(is);
//            BufferedImage image2=ImageIO.read(is2);
//            Graphics g=image.getGraphics();
//            g.drawImage(image2,0,0,null);
//            os = new FileOutputStream(toPath);
//            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
//            enc.encode(image);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(os != null){
//                os.close();
//            }
//            if(is2 != null){
//                is2.close();
//            }
//            if(is != null){
//                is.close();
//            }
//        }
//    }
//
//    /**
//     * 将附加图片合并到底图的右上角
//     * @param negativeImagePath 底图路径
//     * @param additionImagePath 附加图片路径
//     * @param toPath    合成图片写入路径
//     * @throws IOException
//     */
//    public void mergeBothImageToprightcorner(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
//        InputStream is= null;
//        InputStream is2= null;
//        OutputStream os = null;
//        try{
//            is=new FileInputStream(negativeImagePath);
//            is2=new FileInputStream(additionImagePath);
//            BufferedImage image=ImageIO.read(is);
//            BufferedImage image2=ImageIO.read(is2);
//            Graphics g=image.getGraphics();
//            g.drawImage(image2,image.getWidth()-image2.getWidth(),0,null);
//            os = new FileOutputStream(toPath);
//            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
//            enc.encode(image);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(os != null){
//                os.close();
//            }
//            if(is2 != null){
//                is2.close();
//            }
//            if(is != null){
//                is.close();
//            }
//        }
//    }
//
//    /**
//     * 将附加图片合并到底图的左下角
//     * @param negativeImagePath 底图路径
//     * @param additionImagePath 附加图片路径
//     * @param toPath    合成图片写入路径
//     * @throws IOException
//     */
//    public void mergeBothImageLeftbottom(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
//        InputStream is= null;
//        InputStream is2= null;
//        OutputStream os = null;
//        try{
//            is=new FileInputStream(negativeImagePath);
//            is2=new FileInputStream(additionImagePath);
//            BufferedImage image=ImageIO.read(is);
//            BufferedImage image2=ImageIO.read(is2);
//            Graphics g=image.getGraphics();
//            g.drawImage(image2,0,image.getHeight()-image2.getHeight(),null);
//            os = new FileOutputStream(toPath);
//            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
//            enc.encode(image);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(os != null){
//                os.close();
//            }
//            if(is2 != null){
//                is2.close();
//            }
//            if(is != null){
//                is.close();
//            }
//        }
//    }
//
//    /**
//     * 将附加图片合并到底图的左下角
//     * @param negativeImagePath 底图路径
//     * @param additionImagePath 附加图片路径
//     * @param toPath    合成图片写入路径
//     * @throws IOException
//     */
//    public void mergeBothImageRightbottom(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
//        InputStream is= null;
//        InputStream is2= null;
//        OutputStream os = null;
//        try{
//            is=new FileInputStream(negativeImagePath);
//            is2=new FileInputStream(additionImagePath);
//            BufferedImage image=ImageIO.read(is);
//            BufferedImage image2=ImageIO.read(is2);
//            Graphics g=image.getGraphics();
//            g.drawImage(image2,image.getWidth()-image2.getWidth(),image.getHeight()-image2.getHeight(),null);
//            os = new FileOutputStream(toPath);
//            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
//            enc.encode(image);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(os != null){
//                os.close();
//            }
//            if(is2 != null){
//                is2.close();
//            }
//            if(is != null){
//                is.close();
//            }
//        }
//    }
//
//    /**
//     * 将附加图片合并到底图的正中央
//     * @param negativeImagePath 底图路径
//     * @param additionImagePath 附加图片路径
//     * @param toPath    合成图片写入路径
//     * @throws IOException
//     */
//    public void mergeBothImageCenter(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
//        InputStream is= null;
//        InputStream is2= null;
//        OutputStream os = null;
//        try{
//            is=new FileInputStream(negativeImagePath);
//            is2=new FileInputStream(additionImagePath);
//            BufferedImage image=ImageIO.read(is);
//            BufferedImage image2=ImageIO.read(is2);
//            Graphics g=image.getGraphics();
//            g.drawImage(image2,image.getWidth()/2-image2.getWidth()/2,image.getHeight()/2-image2.getHeight()/2,null);
//            os = new FileOutputStream(toPath);
//            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
//            enc.encode(image);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(os != null){
//                os.close();
//            }
//            if(is2 != null){
//                is2.close();
//            }
//            if(is != null){
//                is.close();
//            }
//        }
//    }
//
//    /**
//     * 将附加图片合并到底图的上边中央
//     * @param negativeImagePath 底图路径
//     * @param additionImagePath 附加图片路径
//     * @param toPath    合成图片写入路径
//     * @throws IOException
//     */
//    public void mergeBothImageTopcenter(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
//        InputStream is= null;
//        InputStream is2= null;
//        OutputStream os = null;
//        try{
//            is=new FileInputStream(negativeImagePath);
//            is2=new FileInputStream(additionImagePath);
//            BufferedImage image=ImageIO.read(is);
//            BufferedImage image2=ImageIO.read(is2);
//            Graphics g=image.getGraphics();
//            g.drawImage(image2,image.getWidth()/2-image2.getWidth()/2,0,null);
//            os = new FileOutputStream(toPath);
//            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
//            enc.encode(image);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(os != null){
//                os.close();
//            }
//            if(is2 != null){
//                is2.close();
//            }
//            if(is != null){
//                is.close();
//            }
//        }
//    }
//
//    /**
//     * 将附加图片合并到底图的下边中央
//     * @param negativeImagePath 底图路径
//     * @param additionImagePath 附加图片路径
//     * @param toPath    合成图片写入路径
//     * @throws IOException
//     */
//    public void mergeBothImageBottomcenter(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
//        InputStream is= null;
//        InputStream is2= null;
//        OutputStream os = null;
//        try{
//            is=new FileInputStream(negativeImagePath);
//            is2=new FileInputStream(additionImagePath);
//            BufferedImage image=ImageIO.read(is);
//            BufferedImage image2=ImageIO.read(is2);
//            Graphics g=image.getGraphics();
//            g.drawImage(image2,image.getWidth()/2-image2.getWidth()/2,image.getHeight()-image2.getHeight(),null);
//            os = new FileOutputStream(toPath);
//            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
//            enc.encode(image);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(os != null){
//                os.close();
//            }
//            if(is2 != null){
//                is2.close();
//            }
//            if(is != null){
//                is.close();
//            }
//        }
//    }
//
//    /**
//     * 将附加图片合并到底图的左边中央
//     * @param negativeImagePath 底图路径
//     * @param additionImagePath 附加图片路径
//     * @param toPath    合成图片写入路径
//     * @throws IOException
//     */
//    public void mergeBothImageLeftcenter(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
//        InputStream is= null;
//        InputStream is2= null;
//        OutputStream os = null;
//        try{
//            is=new FileInputStream(negativeImagePath);
//            is2=new FileInputStream(additionImagePath);
//            BufferedImage image=ImageIO.read(is);
//            BufferedImage image2=ImageIO.read(is2);
//            Graphics g=image.getGraphics();
//            g.drawImage(image2,0,image.getHeight()/2-image2.getHeight()/2,null);
//            os = new FileOutputStream(toPath);
//            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
//            enc.encode(image);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(os != null){
//                os.close();
//            }
//            if(is2 != null){
//                is2.close();
//            }
//            if(is != null){
//                is.close();
//            }
//        }
//    }
//
//    /**
//     * 将附加图片合并到底图的右边中央
//     * @param negativeImagePath 底图路径
//     * @param additionImagePath 附加图片路径
//     * @param toPath    合成图片写入路径
//     * @throws IOException
//     */
//    public void mergeBothImageRightcenter(String negativeImagePath,String additionImagePath,String toPath ) throws IOException{
//        InputStream is= null;
//        InputStream is2= null;
//        OutputStream os = null;
//        try{
//            is=new FileInputStream(negativeImagePath);
//            is2=new FileInputStream(additionImagePath);
//            BufferedImage image=ImageIO.read(is);
//            BufferedImage image2=ImageIO.read(is2);
//            Graphics g=image.getGraphics();
//            g.drawImage(image2,image.getWidth()-image2.getWidth(),image.getHeight()/2-image2.getHeight()/2,null);
//            os = new FileOutputStream(toPath);
//            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);
//            enc.encode(image);
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            if(os != null){
//                os.close();
//            }
//            if(is2 != null){
//                is2.close();
//            }
//            if(is != null){
//                is.close();
//            }
//        }
//    }
//
//    /**
//     * 图片灰化操作
//     * @param srcImage 读取图片路径
//     * @param toPath    写入灰化后的图片路径
//     * @param imageFormat 图片写入格式
//     */
//    public void grayImage(String srcImage,String toPath,String imageFormat){
//        try{
//            BufferedImage src = ImageIO.read(new File(srcImage));
//            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//            ColorConvertOp op = new ColorConvertOp(cs, null);
//            src = op.filter(src, null);
//            ImageIO.write(src, imageFormat, new File(toPath));
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 在源图片上设置水印文字
//     * @param srcImagePath  源图片路径
//     * @param alpha 透明度（0<alpha<1）
//     * @param font  字体（例如：宋体）
//     * @param fontStyle     字体格式(例如：普通样式--Font.PLAIN、粗体--Font.BOLD )
//     * @param fontSize  字体大小
//     * @param color 字体颜色(例如：黑色--Color.BLACK)
//     * @param inputWords        输入显示在图片上的文字
//     * @param x     文字显示起始的x坐标
//     * @param y     文字显示起始的y坐标
//     * @param imageFormat   写入图片格式（png/jpg等）
//     * @param toPath    写入图片路径
//     * @throws IOException
//     */
//    public void alphaWords2Image(String srcImagePath,float alpha,
//                                 String font,int fontStyle,int fontSize,Color color,
//                                 String inputWords,int x,int y,String imageFormat,String toPath) throws IOException{
//        FileOutputStream fos=null;
//        try {
//            BufferedImage image = ImageIO.read(new File(srcImagePath));
//            //创建java2D对象
//            Graphics2D g2d=image.createGraphics();
//            //用源图像填充背景
//            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null, null);
//            //设置透明度
//            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
//            g2d.setComposite(ac);
//            //设置文字字体名称、样式、大小
//            g2d.setFont(new Font(font, fontStyle, fontSize));
//            g2d.setColor(color);//设置字体颜色
//            g2d.drawString(inputWords, x, y); //输入水印文字及其起始x、y坐标
//            g2d.dispose();
//            fos=new FileOutputStream(toPath);
//            ImageIO.write(image, imageFormat, fos);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            if(fos!=null){
//                fos.close();
//            }
//        }
//    }
//
//    /**
//     * 在源图像上设置图片水印
//     *  ---- 当alpha==1时文字不透明（和在图片上直接输入文字效果一样）
//     * @param srcImagePath  源图片路径
//     * @param appendImagePath   水印图片路径
//     * @param alpha 透明度
//     * @param x     水印图片的起始x坐标
//     * @param y     水印图片的起始y坐标
//     * @param width 水印图片的宽度
//     * @param height        水印图片的高度
//     * @param imageFormat   图像写入图片格式
//     * @param toPath    图像写入路径
//     * @throws IOException
//     */
//    public void alphaImage2Image(String srcImagePath,String appendImagePath,
//                                 float alpha,int x,int y,int width,int height,
//                                 String imageFormat,String toPath) throws IOException{
//        FileOutputStream fos = null;
//        try {
//            BufferedImage image = ImageIO.read(new File(srcImagePath));
//            //创建java2D对象
//            Graphics2D g2d=image.createGraphics();
//            //用源图像填充背景
//            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null, null);
//            //设置透明度
//            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
//            g2d.setComposite(ac);
//            //设置水印图片的起始x/y坐标、宽度、高度
//            BufferedImage appendImage = ImageIO.read(new File(appendImagePath));
//            g2d.drawImage(appendImage, x, y, width, height, null, null);
//            g2d.dispose();
//            fos=new FileOutputStream(toPath);
//            ImageIO.write(image, imageFormat, fos);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            if(fos!=null){
//                fos.close();
//            }
//        }
//    }
//
//    /**
//     * 画单点 ---- 实际上是画一个填充颜色的圆
//     * ---- 以指定点坐标为中心画一个小半径的圆形，并填充其颜色来充当点
//     * @param srcImagePath   源图片颜色
//     * @param x     点的x坐标
//     * @param y     点的y坐标
//     * @param width 填充的宽度
//     * @param height    填充的高度
//     * @param ovalColor 填充颜色
//     * @param imageFormat   写入图片格式
//     * @param toPath    写入路径
//     * @throws IOException
//     */
//    public void drawPoint(String srcImagePath,int x,int y,int width,int height,Color ovalColor,String imageFormat,String toPath) throws IOException{
//        FileOutputStream fos = null;
//        try {
//            //获取源图片
//            BufferedImage image = ImageIO.read(new File(srcImagePath));
//            //根据xy点坐标绘制连接线
//            Graphics2D g2d = image.createGraphics();
//            g2d.setColor(ovalColor);
//            //填充一个椭圆形
//            g2d.fillOval(x, y, width, height);
//            fos = new FileOutputStream(toPath);
//            ImageIO.write(image, imageFormat, fos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            if(fos!=null){
//                fos.close();
//            }
//        }
//    }
//
////    /**
////     * 画一组（多个）点---- 实际上是画一组（多个）填充颜色的圆
////     * ---- 以指定点坐标为中心画一个小半径的圆形，并填充其颜色来充当点
////     * @param srcImagePath  原图片路径
////     * @param pointList 点列表
////     * @param width 宽度
////     * @param height        高度
////     * @param ovalColor 填充颜色
////     * @param imageFormat   写入图片颜色
////     * @param toPath    写入路径
////     * @throws IOException
////     */
////    public void drawPoints(String srcImagePath,List pointList,int width,int height,Color ovalColor,String imageFormat,String toPath) throws IOException{
////        FileOutputStream fos = null;
////        try {
////            //获取源图片
////            BufferedImage image = ImageIO.read(new File(srcImagePath));
////            //根据xy点坐标绘制连接线
////            Graphics2D g2d = image.createGraphics();
////            g2d.setColor(ovalColor);
////            //填充一个椭圆形
////            if(pointList != null){
////                for(int i=0;i<pointList.size();i++){
////                    Point point = (Point)pointList.get(i);
////                    int x = (int) point.getX();
////                    int y = (int) point.getY();
////                    g2d.fillOval(x, y, width, height);
////                }
////            }
////            fos = new FileOutputStream(toPath);
////            ImageIO.write(image, imageFormat, fos);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }finally{
////            if(fos!=null){
////                fos.close();
////            }
////        }
////    }
//
//    /**
//     * 画线段
//     * @param srcImagePath  源图片路径
//     * @param x1    第一个点x坐标
//     * @param y1    第一个点y坐标
//     * @param x2    第二个点x坐标
//     * @param y2    第二个点y坐标
//     * @param lineColor 线条颜色
//     * @param toPath    图像写入路径
//     * @param imageFormat   图像写入格式
//     * @throws IOException
//     */
//    public void drawLine(String srcImagePath,int x1,int y1,int x2,int y2, Color lineColor,String toPath,String imageFormat) throws IOException{
//        FileOutputStream fos = null;
//        try {
//            //获取源图片
//            BufferedImage image = ImageIO.read(new File(srcImagePath));
//            //根据xy点坐标绘制连接线
//            Graphics2D g2d = image.createGraphics();
//            g2d.setColor(lineColor);
//            g2d.drawLine( x1, y1, x2, y2);
//            fos = new FileOutputStream(toPath);
//            ImageIO.write(image, imageFormat, fos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            if(fos!=null){
//                fos.close();
//            }
//        }
//    }
//
//    /**
//     * 画折线 / 线段
//     * ---- 2个点即画线段，多个点画折线
//     * @param srcImagePath  源图片路径
//     * @param xPoints   x坐标数组
//     * @param yPoints   y坐标数组
//     * @param nPoints   点的数量
//     * @param lineColor 线条颜色
//     * @param toPath    图像写入路径
//     * @param imageFormat   图片写入格式
//     * @throws IOException
//     */
//    public  void drawPolyline(String srcImagePath,int[] xPoints, int[] yPoints, int nPoints,Color lineColor,String toPath,String imageFormat) throws IOException{
//        FileOutputStream fos = null;
//        try {
//            //获取源图片
//            BufferedImage image = ImageIO.read(new File(srcImagePath));
//            //根据xy点坐标绘制连接线
//            Graphics2D g2d = image.createGraphics();
//            //设置线条颜色
//            g2d.setColor(lineColor);
//            g2d.drawPolyline(xPoints, yPoints, nPoints);
//            //图像写出路径
//            fos = new FileOutputStream(toPath);
//            ImageIO.write(image, imageFormat, fos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            if(fos!=null){
//                fos.close();
//            }
//        }
//    }
//
//    /**
//     * 绘制折线，并突出显示转折点
//     * @param srcImagePath  源图片路径
//     * @param xPoints   x坐标数组
//     * @param yPoints   y坐标数组
//     * @param nPoints   点的数量
//     * @param lineColor 连线颜色
//     * @param width 点的宽度
//     * @param height        点的高度
//     * @param ovalColor 点的填充颜色
//     * @param toPath    图像写入路径
//     * @param imageFormat   图像写入格式
//     * @throws IOException
//     */
//    public void drawPolylineShowPoints(String srcImagePath,int[] xPoints, int[] yPoints, int nPoints,Color lineColor,int width,int height,Color ovalColor,String toPath,String imageFormat) throws IOException{
//        FileOutputStream fos = null;
//        try {
//            //获取源图片
//            BufferedImage image = ImageIO.read(new File(srcImagePath));
//            //根据xy点坐标绘制连接线
//            Graphics2D g2d = image.createGraphics();
//            //设置线条颜色
//            g2d.setColor(lineColor);
//            //画线条
//            g2d.drawPolyline(xPoints, yPoints, nPoints);
//            //设置圆点颜色
//            g2d.setColor(ovalColor);
//            //画圆点
//            if(xPoints != null){
//                for(int i=0;i<xPoints.length;i++){
//                    int x = xPoints[i];
//                    int y = yPoints[i];
//                    g2d.fillOval(x, y, width, height);
//                }
//            }
//            //图像写出路径
//            fos = new FileOutputStream(toPath);
//            ImageIO.write(image, imageFormat, fos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            if(fos!=null){
//                fos.close();
//            }
//        }
//    }
//
//
//    /**
//     * 绘制一个由 x 和 y 坐标数组定义的闭合多边形
//     * @param srcImagePath 源图片路径
//     * @param xPoints   x坐标数组
//     * @param yPoints   y坐标数组
//     * @param nPoints   坐标点的个数
//     * @param polygonColor  线条颜色
//     * @param imageFormat   图像写入格式
//     * @param toPath    图像写入路径
//     * @throws IOException
//     */
//    public void drawPolygon(String srcImagePath,int[] xPoints,int[] yPoints,int nPoints,Color polygonColor,String imageFormat,String toPath) throws IOException {
//        FileOutputStream fos = null;
//        try {
//            //获取图片
//            BufferedImage image = ImageIO.read(new File(srcImagePath));
//            //根据xy点坐标绘制闭合多边形
//            Graphics2D g2d = image.createGraphics();
//            g2d.setColor(polygonColor);
//            g2d.drawPolygon(xPoints, yPoints, nPoints);
//            fos = new FileOutputStream(toPath);
//            ImageIO.write(image, imageFormat, fos);
//            g2d.dispose();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            if(fos!=null){
//                fos.close();
//            }
//        }
//    }
//
//    /**
//     * 绘制并填充多边形
//     * @param srcImagePath  源图像路径
//     * @param xPoints   x坐标数组
//     * @param yPoints   y坐标数组
//     * @param nPoints   坐标点个数
//     * @param polygonColor  多边形填充颜色
//     * @param alpha 多边形部分透明度
//     * @param imageFormat   写入图形格式
//     * @param toPath    写入图形路径
//     * @throws IOException
//     */
//    public void drawAndAlphaPolygon(String srcImagePath,int[] xPoints,int[] yPoints,int nPoints,Color polygonColor,float alpha,String imageFormat,String toPath) throws IOException{
//        FileOutputStream fos = null;
//        try {
//            //获取图片
//            BufferedImage image = ImageIO.read(new File(srcImagePath));
//            //根据xy点坐标绘制闭合多边形
//            Graphics2D g2d = image.createGraphics();
//            g2d.setColor(polygonColor);
//            //设置透明度
//            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
//            g2d.setComposite(ac);
//            g2d.fillPolygon(xPoints, yPoints, nPoints);
//            fos = new FileOutputStream(toPath);
//            ImageIO.write(image, imageFormat, fos);
//            g2d.dispose();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            if(fos!=null){
//                fos.close();
//            }
//        }
//    }
//
////
////        /**
////         * 图像切割(按指定起点坐标和宽高切割)
////         * @param srcImageFile 源图像地址
////         * @param result 切片后的图像地址
////         * @param x 目标切片起点坐标X
////         * @param y 目标切片起点坐标Y
////         * @param width 目标切片宽度
////         * @param height 目标切片高度
////         */
////        public final static void cut(String srcImageFile, String result,int x, int y, int width, int height) {
////            try {
////                // 读取源图像
////                BufferedImage bi = ImageIO.read(new File(srcImageFile));
////                int srcWidth = bi.getHeight(); // 源图宽度
////                int srcHeight = bi.getWidth(); // 源图高度
////                if (srcWidth > 0 && srcHeight > 0) {
////                    Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
////                    // 四个参数分别为图像起点坐标和宽高
////                    // 即: CropImageFilter(int x,int y,int width,int height)
////                    ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
////                    Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(),cropFilter));
////                    BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
////                    Graphics g = tag.getGraphics();
////                    g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
////
////                    g.dispose();
////                    // 输出为文件
////                    ImageIO.write(tag, "JPEG", new File(result));
////                }
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * 图像切割（指定切片的行数和列数）
////         * @param srcImageFile 源图像地址
////         * @param descDir 切片目标文件夹
////         * @param rows 目标切片行数。默认2，必须是范围 [1, 20] 之内
////         * @param cols 目标切片列数。默认2，必须是范围 [1, 20] 之内
////         */
////        public final static void cut2(String srcImageFile, String descDir, int rows, int cols) {
////            try {
////                if(rows<=0||rows>20) rows = 2; // 切片行数
////                if(cols<=0||cols>20) cols = 2; // 切片列数
////                // 读取源图像
////
////                BufferedImage bi = ImageIO.read(new File(srcImageFile));
////                int srcWidth = bi.getHeight(); // 源图宽度
////                int srcHeight = bi.getWidth(); // 源图高度
////                if (srcWidth > 0 && srcHeight > 0) {
////                    Image img;
////                    ImageFilter cropFilter;
////                    Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
////                    int destWidth = srcWidth; // 每张切片的宽度
////
////                    int destHeight = srcHeight; // 每张切片的高度
////
////                    // 计算切片的宽度和高度
////                    if (srcWidth % cols == 0) {
////                        destWidth = srcWidth / cols;
////                    } else {
////                        destWidth = (int) Math.floor(srcWidth / cols) + 1;
////                    }
////                    if (srcHeight % rows == 0) {
////                        destHeight = srcHeight / rows;
////                    } else {
////                        destHeight = (int) Math.floor(srcWidth / rows) + 1;
////                    }
////                    // 循环建立切片
////                    // 改进的想法:是否可用多线程加快切割速度
////                    for (int i = 0; i < rows; i++) {
////                        for (int j = 0; j < cols; j++) {
////                            // 四个参数分别为图像起点坐标和宽高
////                            // 即: CropImageFilter(int x,int y,int width,int height)
////                            cropFilter = new CropImageFilter(j * destWidth, i * destHeight,
////                                    destWidth, destHeight);
////                            img = Toolkit.getDefaultToolkit().createImage(
////                                    new FilteredImageSource(image.getSource(),
////                                            cropFilter));
////                            BufferedImage tag = new BufferedImage(destWidth,
////                                    destHeight, BufferedImage.TYPE_INT_RGB);
////                            Graphics g = tag.getGraphics();
////                            g.drawImage(img, 0, 0, null); // 绘制缩小后的图
////
////                            g.dispose();
////                            // 输出为文件
////
////                            ImageIO.write(tag, "JPEG", new File(descDir
////                                    + "_r" + i + "_c" + j + ".jpg"));
////                        }
////                    }
////                }
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * 图像切割（指定切片的宽度和高度）
////         * @param srcImageFile 源图像地址
////         * @param descDir 切片目标文件夹
////         * @param destWidth 目标切片宽度。默认200
////         * @param destHeight 目标切片高度。默认150
////         */
////        public final static void cut3(String srcImageFile, String descDir,int destWidth, int destHeight) {
////            try {
////                if(destWidth<=0) destWidth = 200; // 切片宽度
////                if(destHeight<=0) destHeight = 150; // 切片高度
////                // 读取源图像
////
////                BufferedImage bi = ImageIO.read(new File(srcImageFile));
////                int srcWidth = bi.getHeight(); // 源图宽度
////                int srcHeight = bi.getWidth(); // 源图高度
////                if (srcWidth > destWidth && srcHeight > destHeight) {
////                    Image img;
////                    ImageFilter cropFilter;
////                    Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
////                    int cols = 0; // 切片横向数量
////                    int rows = 0; // 切片纵向数量
////                    // 计算切片的横向和纵向数量
////                    if (srcWidth % destWidth == 0) {
////                        cols = srcWidth / destWidth;
////                    } else {
////                        cols = (int) Math.floor(srcWidth / destWidth) + 1;
////                    }
////                    if (srcHeight % destHeight == 0) {
////                        rows = srcHeight / destHeight;
////                    } else {
////                        rows = (int) Math.floor(srcHeight / destHeight) + 1;
////                    }
////                    // 循环建立切片
////                    // 改进的想法:是否可用多线程加快切割速度
////                    for (int i = 0; i < rows; i++) {
////                        for (int j = 0; j < cols; j++) {
////                            // 四个参数分别为图像起点坐标和宽高
////                            // 即: CropImageFilter(int x,int y,int width,int height)
////                            cropFilter = new CropImageFilter(j * destWidth, i * destHeight,destWidth, destHeight);
////                            img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
////                            BufferedImage tag = new BufferedImage(destWidth,destHeight, BufferedImage.TYPE_INT_RGB);
////                            Graphics g = tag.getGraphics();
////                            g.drawImage(img, 0, 0, null); // 绘制缩小后的图
////                            g.dispose();
////                            // 输出为文件
////                            ImageIO.write(tag, "JPEG", new File(descDir + "_r" + i + "_c" + j + ".jpg"));
////                        }
////                    }
////                }
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * 图像类型转换：GIF->JPG、GIF->PNG、PNG->JPG、PNG->GIF(X)、BMP->PNG
////         * @param srcImageFile 源图像地址
////         * @param formatName 包含格式非正式名称的 String：如JPG、JPEG、GIF等
////
////         * @param destImageFile 目标图像地址
////         */
////        public final static void convert(String srcImageFile, String formatName, String destImageFile) {
////            try {
////                File f = new File(srcImageFile);
////                f.canRead();
////                f.canWrite();
////                BufferedImage src = ImageIO.read(f);
////                ImageIO.write(src, formatName, new File(destImageFile));
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * 彩色转为黑白
////         * @param srcImageFile 源图像地址
////         * @param destImageFile 目标图像地址
////         */
////        public final static void gray(String srcImageFile, String destImageFile) {
////            try {
////                BufferedImage src = ImageIO.read(new File(srcImageFile));
////                ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
////                ColorConvertOp op = new ColorConvertOp(cs, null);
////                src = op.filter(src, null);
////                ImageIO.write(src, "JPEG", new File(destImageFile));
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * 给图片添加文字水印
////         * @param pressText 水印文字
////         * @param srcImageFile 源图像地址
////         * @param destImageFile 目标图像地址
////         * @param fontName 水印的字体名称
////         * @param fontStyle 水印的字体样式
////         * @param color 水印的字体颜色
////         * @param fontSize 水印的字体大小
////         * @param x 修正值
////         * @param y 修正值
////         * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
////         */
////        public final static void pressText(String pressText, String srcImageFile, String destImageFile, String fontName,int fontStyle, Color color, int fontSize,int x,int y, float alpha) {
////            try {
////                File img = new File(srcImageFile);
////                Image src = ImageIO.read(img);
////                int width = src.getWidth(null);
////                int height = src.getHeight(null);
////                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
////                Graphics2D g = image.createGraphics();
////                g.drawImage(src, 0, 0, width, height, null);
////                g.setColor(color);
////                g.setFont(new Font(fontName, fontStyle, fontSize));
////                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
////                // 在指定坐标绘制水印文字
////                g.drawString(pressText, (width - (getLength(pressText) * fontSize))/ 2 + x, (height - fontSize) / 2 + y);
////                g.dispose();
////                ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));// 输出到文件流
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * 给图片添加文字水印
////         * @param pressText 水印文字
////         * @param srcImageFile 源图像地址
////         * @param destImageFile 目标图像地址
////         * @param fontName 字体名称
////         * @param fontStyle 字体样式
////         * @param color 字体颜色
////         * @param fontSize 字体大小
////         * @param x 修正值
////         * @param y 修正值
////         * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
////         */
////        public final static void pressText2(String pressText, String srcImageFile,String destImageFile, String fontName, int fontStyle, Color color, int fontSize, int x, int y, float alpha) {
////            try {
////                File img = new File(srcImageFile);
////                Image src = ImageIO.read(img);
////                int width = src.getWidth(null);
////                int height = src.getHeight(null);
////                BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
////                Graphics2D g = image.createGraphics();
////                g.drawImage(src, 0, 0, width, height, null);
////                g.setColor(color);
////                g.setFont(new Font(fontName, fontStyle, fontSize));
////                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
////                // 在指定坐标绘制水印文字
////                g.drawString(pressText, (width - (getLength(pressText) * fontSize))/ 2 + x, (height - fontSize) / 2 + y);
////                g.dispose();
////                ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////        /**
////         * 给图片添加图片水印
////         * @param pressImg 水印图片
////         * @param srcImageFile 源图像地址
////         * @param destImageFile 目标图像地址
////         * @param x 修正值。 默认在中间
////         * @param y 修正值。 默认在中间
////         * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
////         */
////        public final static void pressImage(String pressImg, String srcImageFile,String destImageFile, int x, int y, float alpha) {
////            try {
////                File img = new File(srcImageFile);
////                Image src = ImageIO.read(img);
////                int wideth = src.getWidth(null);
////                int height = src.getHeight(null);
////                BufferedImage image = new BufferedImage(wideth, height,BufferedImage.TYPE_INT_RGB);
////                Graphics2D g = image.createGraphics();
////                g.drawImage(src, 0, 0, wideth, height, null);
////                // 水印文件
////                Image src_biao = ImageIO.read(new File(pressImg));
////                int wideth_biao = src_biao.getWidth(null);
////                int height_biao = src_biao.getHeight(null);
////                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
////                g.drawImage(src_biao, (wideth - wideth_biao) / 2,(height - height_biao) / 2, wideth_biao, height_biao, null);
////                // 水印文件结束
////                g.dispose();
////                ImageIO.write((BufferedImage) image,  "JPEG", new File(destImageFile));
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * 创建图片缩略图(等比缩放 无失真缩放)
////         * @param src 源图片文件完整路径
////         * @param dist 目标图片文件完整路径
////         * @param width 缩放的宽度
////         * @param height 缩放的高度
////         * @param flag  true 按照实际长宽输出  如果 false 按照比例进行无失真压缩
////         */
////        public static boolean createThumbnail(String src, String dist, float width, float height,boolean flag) {
////            boolean flag1 = false ;
////            try {
////                File srcfile = new File(src);
////                if (!srcfile.exists()) {
////                    System.out.println("文件不存在");
////                    return flag1;
////                }
////                BufferedImage image = ImageIO.read(srcfile);
////
////                // 获得缩放的比例
////                double ratio = 1.0;
////                // 判断如果高、宽都不大于设定值，则不处理
////                if (image.getHeight() > height || image.getWidth() > width) {
////                    if (image.getHeight() > image.getWidth()) {
////                        ratio = height / image.getHeight();
////                    } else {
////                        ratio = width / image.getWidth();
////                    }
////                }
////                int newWidth = flag ? (int) width : (int) (image.getWidth() * ratio);
////                int newHeight = flag ? (int)height : (int) (image.getHeight() * ratio);
////                BufferedImage bfImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
////                flag1 = bfImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
////                FileOutputStream os = new FileOutputStream(dist);
////                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
////                JPEGEncodeParam jParam = encoder.getDefaultJPEGEncodeParam(bfImage) ;
////                jParam.setQuality(1f, false) ;
////                encoder.encode(bfImage);
////                os.close();
////                flag1 = true ;
////            } catch (Exception e) {
////                flag1 = false ;
////            }
////            return flag1 ;
////        }
////
////        /**
////         * 计算text的长度（一个中文算两个字符）
////         * @param text
////         * @return
////         */
////        public final static int getLength(String text) {
////            int length = 0;
////            for (int i = 0; i < text.length(); i++) {
////                if (new String(text.charAt(i) + "").getBytes().length > 1) {
////                    length += 2;
////                } else {
////                    length += 1;
////                }
////            }
////            return length / 2;
////        }
////
////        /**
////         * <获取图片宽度>
////         * add by jiang_yanyan 2015-01-04
////         * @param file  图片文件
////         * @return 宽度
////         */
////        public static int getImgWidth(File file) {
////            InputStream is = null;
////            BufferedImage src = null;
////            int ret = -1;
////            try {
////                is = new FileInputStream(file);
////                src = javax.imageio.ImageIO.read(is);
////                ret = src.getWidth(null); // 得到源图宽
////                is.close();
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////            return ret;
////        }
////
////        /**
////         * <获取图片高度>
////         * add by jiang_yanyan 2015-01-04
////         * @param file  图片文件
////         * @return 高度
////         */
////        public static int getImgHeight(File file) {
////            InputStream is = null;
////            BufferedImage src = null;
////            int ret = -1;
////            try {
////                is = new FileInputStream(file);
////                src = javax.imageio.ImageIO.read(is);
////                ret = src.getHeight(null); // 得到源图高
////                is.close();
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////            return ret;
////        }
////    }
}
