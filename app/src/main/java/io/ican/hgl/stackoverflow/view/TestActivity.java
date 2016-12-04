package io.ican.hgl.stackoverflow.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import io.ican.hgl.stackoverflow.R;
import io.ican.hgl.stackoverflow.engineer.JsoupEngineer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        JsoupEngineer.MAIN_PAGE("https://stackoverflow.com/questions/40958897/rendering-an-isometric-grid-in-pygame")
                .map(new Func1<Document, Element>() {
                    @Override
                    public Element call(Document document) {
                        return document.getElementsByTag("code").first();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Element>() {
                    @Override
                    public void call(Element element) {
                        HtmlTextView view = (HtmlTextView) findViewById(R.id.html_text);
                        TextView textView = (TextView) findViewById(R.id.common_text);
                        Spanned html = Html.fromHtml(element.html(),Html.FROM_HTML_MODE_COMPACT);
                        textView.setText(html);
                    }
                });
    }
}
