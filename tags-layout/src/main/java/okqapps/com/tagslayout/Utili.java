package okqapps.com.tagslayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

class Utili {
    static Utili mInstance;
    static Toast toast;

    public static Utili getInstance() {
        if (mInstance == null) {
            mInstance = new Utili();
        }
        return mInstance;
    }

    /**
     * This method will show custom toast message to user
     *
     * @param message resource id for message string
     */
    public void showToastMessage(Context context, String message) {
        View layout = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);

        TextView text = layout.findViewById(R.id.textView_toast_message);
        text.setText(message);
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(context);
//        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
