package news.nitesh.com.news;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by nitesh on 1/6/2017.
 */

public class IBinderService extends Service {

    IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public IBinderService getServerInstance() {
            return IBinderService.this;
        }
    }

    public String getTime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String value = mDateFormat.format(new Date());
        if (value != null)
            value = mDateFormat.format(new Date());
        else
            value = "no value";
        return value;
    }
}
