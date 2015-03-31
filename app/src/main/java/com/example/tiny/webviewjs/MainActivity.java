package com.example.tiny.webviewjs;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiny on 2015/3/23.
 */
public class MainActivity extends RoboActivity {
    @InjectView(R.id.webview)
    private WebView webview;

    @InjectView(R.id.lv_image)
    private ListView lv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String data = getFromAssets("test.html");
        webview.loadData(data, "text/html", "utf-8");
        JavaScriptInterface jsInterface = new JavaScriptInterface();
        webview.addJavascriptInterface(jsInterface, "JSInterface");

        ImageLoader.getInstance().clearDiskCache();
        ImageLoader.getInstance().clearMemoryCache();

        List<ImageBean> datas = new ArrayList<ImageBean>();
        ImageBean bean = new ImageBean();
        bean.setUrl("http://e.hiphotos.baidu.com/image/w%3D2048/sign=e5c657e86e81800a6ee58e0e850d32fa/d788d43f8794a4c24980494c0cf41bd5ad6e3931.jpg");
        datas.add(bean);
        bean = new ImageBean();
        bean.setUrl("http://e.hiphotos.baidu.com/image/w%3D2048/sign=35ba1a9c3bdbb6fd255be2263d1caa18/42a98226cffc1e17eee222564890f603738de97c.jpg");
        datas.add(bean);
        bean = new ImageBean();
        bean.setUrl("http://a.hiphotos.baidu.com/image/w%3D2048/sign=21672769b33533faf5b6942e9cebfc1f/7c1ed21b0ef41bd595d1469353da81cb38db3dd2.jpg");
        datas.add(bean);
        bean = new ImageBean();
        bean.setUrl("http://e.hiphotos.baidu.com/image/w%3D2048/sign=be153d49aad3fd1f3609a53a0476241f/ac6eddc451da81cb05452daa5066d01609243131.jpg");
        datas.add(bean);
        bean = new ImageBean();
        bean.setUrl("http://a.hiphotos.baidu.com/image/w%3D2048/sign=0bfc05060db30f24359aeb03fcadd043/b151f8198618367aaac1d1ba2c738bd4b31ce509.jpg");
        datas.add(bean);
        bean = new ImageBean();
        bean.setUrl("http://g.hiphotos.baidu.com/image/w%3D2048/sign=966c66484334970a4773172fa1f2d0c8/50da81cb39dbb6fd6cd94b410b24ab18962b37d2.jpg");
        datas.add(bean);
        bean = new ImageBean();
        bean.setUrl("http://c.hiphotos.baidu.com/image/w%3D2048/sign=8987acd4a964034f0fcdc5069bfb7831/060828381f30e9247ae5fe384e086e061d95f772.jpg");
        datas.add(bean);
        bean = new ImageBean();
        bean.setUrl("http://c.hiphotos.baidu.com/image/w%3D2048/sign=f6577f0d369b033b2c88fbda21f637d3/a2cc7cd98d1001e91b7c7606ba0e7bec54e79772.jpg");
        datas.add(bean);
        bean = new ImageBean();
        bean.setUrl("http://e.hiphotos.baidu.com/image/w%3D2048/sign=6d7a1ca9f403738dde4a0b228723b151/a8ec8a13632762d02378c7b6a2ec08fa513dc671.jpg");
        datas.add(bean);
        ImageAdapter mAdapter = new ImageAdapter(this, datas, R.layout.item_lv_image);
        lv_image.setAdapter(mAdapter);
        lv_image.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), true, false));
    }

    private class ImageAdapter<T> extends CustomAdapter<T> {

        DisplayImageOptions options;

        public ImageAdapter(Context c, List<T> datas, int itemID) {
            super(c, datas, itemID);
            //显示图片的配置
            options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.abc_ab_share_pack_holo_dark)
                    .showImageOnFail(R.drawable.ic_launcher)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }

        @Override
        public void convert(ViewHolder helper, T o) {
            ImageView iv = helper.getView(R.id.iv_image);
            ImageBean bean = (ImageBean) o;
            ImageLoader.getInstance().displayImage(bean.getUrl(), iv, options);
        }
    }

    public class JavaScriptInterface {
        @JavascriptInterface
        public void startVideo() {
            Toast.makeText(MainActivity.this, "测试调用java", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 把assets文件夹中的文本读取为String
     *
     * @param fileName
     * @return
     */
    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            if (bufReader != null)
                bufReader.close();
            if (inputReader != null)
                inputReader.close();
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
