package com.mark.service.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by fellowlei on 2018/5/3.
 */
public class PdfDemo {
    public static void main(String[] args) throws Exception {
//        demo1();
//        demo2();
//        demo3();
//        demo4();
//        demo5();
//        demo6();
//        tmp();
        genReport();
    }


    public static void genReport() throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("d:/tmp/report.pdf"));
        document.open();

        Paragraph paragraph1 = new Paragraph("first",getChineseFont());
        Paragraph paragraph2 = new Paragraph("second",getChineseFont());
        Paragraph paragraph3 = new Paragraph("third",getChineseFont());

        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);

        // 图片Image对象
        Image img = Image.getInstance("d:/tmp/bar_3d.jpeg");
        img.scaleToFit(400, 400);// 大小
        document.add(img);
        Paragraph paragraph4 = new Paragraph("third\n",getChineseFont());
        document.add(paragraph4);



        PdfPTable table = new PdfPTable(2);
        // 添加表头元素
        for (int i = 0; i < 2; i++) {
            table.addCell(new Paragraph(i+"header", getChineseFont()));
        }
        // 添加表格的内容
        for(int i=0; i<5; i++){
            for (int j = 0; j < 2; j++) {
                table.addCell(new Paragraph("content" + i + ":" + j, getChineseFont()));
            }
        }


        document.add(table);

        document.close();
    }



    public static void demo6() throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, getOutput());
        document.open();

        /*
         * PDF分为四层，第一层和第四层由低级操作来进行操作，第二层、第三层由高级对象操作(从下往上)
         * 第一层操作只能使用PdfWriter.DirectContent操作，第四层使用DirectContentUnder操作,。
         * 第二层和第三层的PdfContentByte是由IText内部操作，没有提供api接口。
         */
        PdfContentByte under = writer.getDirectContentUnder();
        // under = writer.getDirectContent();

        under.beginText();
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
        under.setFontAndSize(bf, 18);
        // under.setTextMatrix(30, 30);
        under.showTextAligned(Element.ALIGN_LEFT, "ShuiYin................", 230, 430, 45);
        under.endText();

        document.close();
    }

    public static void demo5() throws DocumentException, FileNotFoundException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, getOutput());
        document.open();

        PdfPTable table = new PdfPTable(6);
        // 添加表头元素
        for (int i = 0; i < 6; i++) {
            table.addCell(new Paragraph(i+"header", getChineseFont()));
        }
        // 添加表格的内容
        for (int i = 0; i < 6; i++) {
            table.addCell(new Paragraph(i+"content", getChineseFont()));
        }
        table.setSpacingBefore(10f);// 设置表格上面空白宽度

        // 1-表格的宽度和布局
        table.setHorizontalAlignment(Element.ALIGN_LEFT);// 居左
        table.setTotalWidth(369.7f);// 表格的总宽度
        table.setWidths(new float[] { 0.1565f, 0.15f, 0.15f, 0.145f, 0.15f, 0.145f });// 单元格宽度
        table.setWidthPercentage(100);// 设置表格宽度为%100
        // table.setLockedWidth(true);// 宽度锁定,不锁定，下面有变化
        document.add(table);
        document.add(new Paragraph("\n\n"));

        // 居中
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        document.add(table);
        document.add(new Paragraph("\n\n"));

        // 居右
        table.setWidthPercentage(50);// 宽度减半
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        document.add(table);
        document.add(new Paragraph("\n\n"));

        // 固定宽度
        table.setTotalWidth(300);
        table.setLockedWidth(true);// 锁定宽度
        document.add(table);

        // 2-表格的边框、高度、设置单元格颜色 、前后距离
        PdfPCell cell = new PdfPCell(new Paragraph("合并3个单元格", getChineseFont()));
        cell.setColspan(3);
        cell.setBackgroundColor(BaseColor.CYAN);
        cell.setMinimumHeight(30f);// 最小高度
        cell.setFixedHeight(40f);// 固定高度
        table.addCell(cell);

        // 单元格内文本
        Paragraph tParagraph = new Paragraph("居中", getChineseFont());
        tParagraph.setAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(tParagraph);
        cell.setBorderColor(new BaseColor(255, 0, 0)); // 边框 ，下面的表格有可能会覆盖
        cell.setFixedHeight(45f);// 固定高度，覆盖前面的固定高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        table.addCell(cell);

        // 单元格背景色
        cell = new PdfPCell(new Paragraph("无边框", getChineseFont()));
        cell.setBorder(Rectangle.NO_BORDER);// 无边框
        cell.setBorderWidth(0);// 无边框
        table.addCell(cell);

        // 边框颜色
        cell = new PdfPCell(new Paragraph("单元格边框颜色", getChineseFont()));
        cell.setBorderColor(BaseColor.YELLOW);
        table.addCell(cell);

        document.add(new Paragraph("使用'SpacingBefore'和'setSpacingAfter'", getChineseFont()));
        table.setSpacingBefore(15f); // 前距离
        document.add(table);
        table.setSpacingAfter(15f); // 后距离
        document.add(table);

        // 3-写入文档的绝对位置
        // 参数rowStart是你想开始的行的数目，参数rowEnd是你想显示的最后的行（如果你想显示所有的行，用-1），
        // xPos和yPos是表格的坐标，canvas是一个PdfContentByte对象。

        document.add(new Paragraph(
                "写入文档的绝对位置:(writeSelectedRows(rowStart, rowEnd, xPos, yPos, canvas))参数rowStart是你想开始的行的数目，参数rowEnd是你想显示的最后的行（如果你想显示所有的行，用-1），xPos和yPos是表格的坐标，canvas是一个PdfContentByte对象。",
                getChineseFont()));
        PdfContentByte tContent = writer.getDirectContent();
        table.writeSelectedRows(0, -1, 0, -1, 100, 200, tContent);

        document.add(new Paragraph("第1行到最后,从0开始计数", getChineseFont()));
        table.writeSelectedRows(1, -1, 100, 100, tContent);
        document.close();
    }

    public static void demo4() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        // 使用PDFWriter进行写文件操作
        PdfWriter.getInstance(document, getOutput());
        document.open();

        // 中文字体(现在高版本的不支持中文包)
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);// 中文、12、正常

        int colNumber = 6;

        // PdfPTable[PdfPTable[PdfPCell[Paragraph]]]
        // 创建有6列的表格
        PdfPTable datatable = new PdfPTable(colNumber);
        // 定义表格的宽度
        int[] cellsWidth = { 1, 1, 1, 1, 1, 1 };
        datatable.setWidths(cellsWidth);// 单元格宽度
        // datatable.setTotalWidth(300f);//表格的总宽度
        datatable.setWidthPercentage(100);// 表格的宽度百分比

        datatable.getDefaultCell().setPadding(2);// 单元格的间隔
        datatable.getDefaultCell().setBorderWidth(2);// 边框宽度
        // 设置表格的底色
        datatable.getDefaultCell().setBackgroundColor(BaseColor.GREEN);
        datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        // PdfPTable[PdfPCell[Paragraph]]
        // 添加表头元素
        for (int i = 0; i < colNumber; i++) {
            datatable.addCell(new Paragraph(i+"head", fontChinese));
        }
        // 添加表格的内容
        for (int i = 0; i < colNumber; i++) {
            datatable.addCell(new Paragraph(i+"col", fontChinese));
        }

        // 空白表格
        for (int i = 0; i < colNumber; i++) {
            PdfPCell cell = new PdfPCell(new Paragraph(""));
            cell.setFixedHeight(10);// 单元格高度
            datatable.addCell(cell);
        }
        datatable.setSpacingAfter(40f);// 设置表格下面空白行
        document.add(datatable);// 把表格加入文档

        // 跨行跨列表格
        PdfPTable table = new PdfPTable(3); // 3列表格
        PdfPCell cell; // 单元格
        cell = new PdfPCell(new Phrase("跨3列", getChineseFont()));
        cell.setColspan(3);// 跨3列
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("跨2行", getChineseFont()));
        cell.setRowspan(2);// 跨2行
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");

        document.add(table);

        // 表格的嵌套
        PdfPTable tableFather = new PdfPTable(4);
        tableFather.setSpacingBefore(20f);// 设置表格上面空白行
        // 1行2列
        PdfPTable nested1 = new PdfPTable(2);
        nested1.addCell("1.1");
        nested1.addCell("1.2");
        // 2行1列
        PdfPTable nested2 = new PdfPTable(1);
        nested2.addCell("2.1");
        nested2.addCell("2.2");

        // 将表格插入到指定位置
        for (int k = 0; k < 12; ++k) {
            if (k == 1) {
                tableFather.addCell(nested1);
            } else if (k == 6) {
                tableFather.addCell(nested2);
            } else {
                tableFather.addCell("cell " + k);
            }
        }
        document.add(tableFather);

        document.close();
    }

    public static void tmp() throws  Exception{
        // 1- 页面的属性
        Rectangle tRectangle = new Rectangle(PageSize.A4); // 页面大小
        Document doc = new Document(tRectangle.rotate());// 横向打印

        PdfWriter writer = PdfWriter.getInstance(doc, getOutput());// 书写器

        // PDF版本(默认1.4)
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);

        // 2-PDF文档属性
        doc.addTitle("Title@sample");// 标题
        doc.addAuthor("Author@rensanning");// 作者
        doc.addSubject("Subject@iText sample");// 主题
        doc.addKeywords("Keywords@iText");// 关键字
        doc.addCreator("Creator@iText");// 谁创建的

        // 3-综合属性：
        doc.setMargins(10, 20, 30, 40);// 页边空白

        doc.open();// 打开文档
        doc.add(new Paragraph("Hello World"));// 添加内容

        Image img = Image.getInstance("d:/tmp/test.jpg");
        img.setAlignment(Image.LEFT);
        img.setBorder(Image.BOX);
        img.setBorderWidth(10);
        img.setBorderColor(BaseColor.WHITE);
        img.scaleToFit(1000, 100);// 大小
        img.setRotationDegrees(-30);// 旋转
        doc.add(img);

//        // 4-添加下一页
//        doc.newPage();
//        writer.setPageEmpty(true);// fasle-显示空内容的页;true-不会显示
//
//        doc.newPage();
//        doc.add(new Paragraph("New page"));

        doc.close();
    }

    public static void demo3() throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, getOutput());
        document.open();

        // Anchor超链接和锚点对象: internal and external links
        Paragraph country = new Paragraph();
        Anchor dest = new Anchor("我是锚点，也是超链接", getChineseFont());
        dest.setName("CN"); // 设置锚点的名字
        dest.setReference("http://www.china.com");// 连接
        country.add(dest);
        country.add(String.format(": %d sites", 10000));
        document.add(country);
//
        Anchor toUS = new Anchor("连接到设置的CN锚点。", getChineseFont());
        toUS.setReference("#CN");// 取到锚点
        document.add(toUS);

        // 图片Image对象
        Image img = Image.getInstance("d:/tmp/test.jpg");
        img.setAlignment(Image.LEFT);
        img.setBorder(Image.BOX);
        img.setBorderWidth(10);
        img.setBorderColor(BaseColor.WHITE);
        img.scaleToFit(1000, 72);// 大小
        img.setRotationDegrees(-30);// 旋转
        document.add(img);

        // Chapter, Section对象（目录对象）
        Paragraph title = new Paragraph("一级标题", getChineseFont());
        Chapter chapter = new Chapter(title, 1);

        Paragraph title2 = new Paragraph("二级标题1", getChineseFont());
        Section section = chapter.addSection(title2);
        section.setBookmarkTitle("bmk");// 左边目录显示的名字，不写就默认名
        section.setIndentation(30);
        section.setIndentationLeft(5);
        section.setBookmarkOpen(false);
        section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);

        Section section2 = chapter.addSection(new Paragraph("二级标题2", getChineseFont()));
        section2.setIndentation(30);
        section2.setIndentationLeft(5);
        section2.setBookmarkOpen(false);
        section2.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);

        Section subsection = section.addSection(new Paragraph("三级标题1", getChineseFont()));
        subsection.setIndentationLeft(10);
        // subsection.setNumberDepth(1);
        subsection.setNumberStyle(Section.NUMBERSTYLE_DOTTED);

        Section subsection2 = section2.addSection(new Paragraph("三级标题2", getChineseFont()));
        subsection2.setIndentationLeft(10);
        subsection2.setNumberStyle(Section.NUMBERSTYLE_DOTTED);
        document.add(chapter);

        document.close();
    }

    public static OutputStream getOutput() throws FileNotFoundException {
        FileOutputStream fileOutputStream  =  new FileOutputStream("d:/tmp/test.pdf");
        return fileOutputStream;
    }

    public static void demo1() throws  Exception{
        // 1- 页面的属性
        Rectangle tRectangle = new Rectangle(PageSize.A4); // 页面大小
        // tRectangle = new Rectangle(0, 0, 800, 600);

        tRectangle.setBackgroundColor(BaseColor.ORANGE); // 页面背景色
        tRectangle.setBorder(1220);// 边框
        tRectangle.setBorderColor(BaseColor.BLUE);// 边框颜色
        tRectangle.setBorderWidth(244.2f);// 边框宽度

        Document doc = new Document(tRectangle);// 定义文档
        doc = new Document(tRectangle.rotate());// 横向打印

        PdfWriter writer = PdfWriter.getInstance(doc, getOutput());// 书写器

        // PDF版本(默认1.4)
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);

        // 2-PDF文档属性
        doc.addTitle("Title@sample");// 标题
        doc.addAuthor("Author@rensanning");// 作者
        doc.addSubject("Subject@iText sample");// 主题
        doc.addKeywords("Keywords@iText");// 关键字
        doc.addCreator("Creator@iText");// 谁创建的

        // 3-综合属性：
        doc.setMargins(10, 20, 30, 40);// 页边空白

        doc.open();// 打开文档
        doc.add(new Paragraph("Hello World"));// 添加内容

        // 4-添加下一页
        doc.newPage();
        writer.setPageEmpty(true);// fasle-显示空内容的页;true-不会显示

        doc.newPage();
        doc.add(new Paragraph("New page"));

        doc.close();
    }

    public static void demo2() throws DocumentException, FileNotFoundException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, getOutput());
        document.open();

        // 1-Chunk块对象: a String, a Font, and some attributes
        document.add(new Chunk("中文输出： ", getChineseFont()));

        Chunk tChunk2 = new Chunk("输出的内容", getChineseFont());
        tChunk2.setBackground(BaseColor.CYAN, 1f, 0.5f, 1f, 1.5f); // 设置背景色
        tChunk2.setTextRise(6); // 上浮
        tChunk2.setUnderline(0.2f, -2f); // 下划线
        document.add(tChunk2);

        document.add(Chunk.NEWLINE); // 新建一行
        // document.add(new Phrase("Phrase page  :")); //会上浮，不知道原因？？

        // 2-Phrase短语对象: a List of Chunks with leading
        document.add(new Phrase("Phrase page  :"));

        Phrase tPhrase = new Phrase();
        Chunk name = new Chunk("China");
        name.setUnderline(0.2f, -2f);
        tPhrase.add(name);
        tPhrase.add(Chunk.NEWLINE);// 放在容器中好用
        tPhrase.add(new Chunk("换行了 :", getChineseFont()));
        tPhrase.add(new Chunk("chinese"));
        tPhrase.setLeading(14f);// 行间距
        document.add(tPhrase);

        // 这边就好用，上面是Chunk，就不好用
        // 放在段落或短语中又好用
        document.add(Chunk.NEWLINE);

        Phrase director2 = new Phrase();
        Chunk name2 = new Chunk("换行了---Japan", getChineseFont());
        name2.setUnderline(0.2f, -2f);
        director2.add(name2);
        director2.add(new Chunk(","));
        director2.add(new Chunk(" "));
        director2.add(new Chunk("japanese上浮下", getChineseFont()).setTextRise(3f));
        director2.setLeading(24);
        document.add(director2);

        // 3-Paragraph段落对象: a Phrase with extra properties and a newline
        document.add(new Paragraph("Paragraph page"));
        Paragraph info = new Paragraph();
        info.add(new Chunk("China "));
        info.add(new Chunk("chinese"));
        info.add(Chunk.NEWLINE); // 好用的
        info.add(new Phrase("Japan "));
        info.add(new Phrase("japanese"));
        info.setSpacingAfter(10f);// 设置段落下空白
        document.add(info);

        // 段落是比较好用的
        Paragraph tParagraph = new Paragraph("段落是文章中最基本的单位。内容上它具有一个相对完整的意思；在文章中，段具有换行的标。段是由句子或句群组成的，在文章中用于体现作者的思路发展或全篇文章的层次。有的段落只有一个句子，称为独句段，独句段一般是文章的开头段、结尾段、"
                + "过渡段强调段等特殊的段落。多数段落包括不止一个句子或句群，叫多句段。中文段落开头前一般空两个格。", getChineseFont());
        tParagraph.setAlignment(Element.ALIGN_JUSTIFIED);// 对齐方式

        tParagraph.setIndentationLeft(12);// 左缩进
        tParagraph.setIndentationRight(12);// 右缩进
        tParagraph.setFirstLineIndent(24);// 首行缩进

        tParagraph.setLeading(20f);// 行间距
        tParagraph.setSpacingBefore(5f);// 设置上空白
        tParagraph.setSpacingAfter(10f);// 设置段落下空白
        document.add(tParagraph);

        // 每个新的段落会另起一行
        tParagraph = new Paragraph("新的段落", getChineseFont());
        tParagraph.setAlignment(Element.ALIGN_CENTER);// 居中
        document.add(tParagraph);

        document.close();
    }


    public static Font getChineseFont() {
        BaseFont bfChinese;
        Font fontChinese = null;
        try {
            bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            // fontChinese = new Font(bfChinese, 12, Font.NORMAL);
            fontChinese = new Font(bfChinese, 12, Font.NORMAL, BaseColor.BLUE);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fontChinese;

    }
}
