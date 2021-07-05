import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 验证正则表达式
 */
@SpringBootTest
public class TestRegexRules {

    @Test
    public void testPicUrl() {
        String picUrlHtml =
                "<div id=\"sidebar\">\n" +
                        "\t\t\t\t<div id=\"fmimg\">\n" +
                        "                    <img alt=\"噬天至尊\" src=\"/BookFiles/BookImages/98549.jpg\" width=\"120\" height=\"150\" /><span class=\"b\"></span></div>\n" +
                        "                <div id=\"reader\"><a class=\"download\" href=\"/ibook/txt/98549.html\" title=\"离线阅读\"></a></div>\n" +
                        "\t\t\t</div> " ;
        //src="\s+src="([^>]+.jpg)""
        Pattern picUrlPatten = compile("<img alt=\".*\" src=\"(.*?)\" width=\"120\" height=\"150\" />");
        Matcher picUrlMatch = picUrlPatten.matcher(picUrlHtml);
        boolean isFindpicUrl = picUrlMatch.find();
        if (isFindpicUrl) {
            String picUrl = picUrlMatch.group(1);
            System.out.println("picUrl = " + picUrl);
        }
    }

    @Test
    public void testAuthorName() {
        String bookDetailHtml =
                "            <div class=\"panel-intro\">\n" +
                        "                <h1 class=\"btitle\">猛卒</h1>\n" +
                        "                <div class=\"infos\">\n" +
                        "                    <span>作者：<a href=\"https://www.xqb5200.com/modules/article/authorarticle.php?author=高月\" title=\"高月作品全集\" target=\"_blank\">高月</a></span>\n" +
                        "                    <span class=\"item\">写作状态：连载中(共475万字)</span>\n" +
                        "                    <span class=\"item\"><b>最新章节：</b><a class=\"red\" href=\"47913868.html\">正文 第七百二十八章 民间建议</a> (更新时间：2020-05-19 7:02)</span>\n" +
                        "                </div>\n" +
                        "                <p class=\"intro\">\n" +
                        "                    &nbsp;&nbsp;这是一个迷失在乱世中的大唐，中原藩镇割据，边疆危机四伏，内忧外患，长安却夜夜笙歌，罗裙慢舞。<br />\n" +
                        "&nbsp;&nbsp;他是一个独孤的猛卒，鹰镝千里，强悍的游牧勇士闻之丧胆，但大唐却没有他的荣耀。<br />\n" +
                        "&nbsp;&nbsp;他拔剑茫然，英雄无觅归处<br />\n" +
                        "&nbsp;&nbsp;。。。。。。<br />\n" +
                        "\n" +
                        "                </p>\n" +
                        "            </div>\n";
        //href="https://www.xqb5200.com/modules/article/authorarticle.php?author=([^/]+)" title=
        Pattern authorNamePatten = compile("href=\"https://www.xqb5200.com/modules/article/authorarticle.php\\?author=([^/]+)\" title=");
        Matcher authorNameMatch = authorNamePatten.matcher(bookDetailHtml);
        boolean isFindAuthorName = authorNameMatch.find();
        if (isFindAuthorName) {
            String authorName = authorNameMatch.group(1);
            System.out.println("authorName = " + authorName);
        }
    }
}
