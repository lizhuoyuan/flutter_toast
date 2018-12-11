package zhuoyuan.li.fluttertoast;

import android.app.Activity;
import android.widget.Toast;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterToastPlugin */
public class FlutterToastPlugin implements MethodCallHandler {

  private final MethodChannel channel;
  private Activity activity;

  private FlutterToastPlugin(MethodChannel channel, Activity activity) {
    this.channel = channel;
    this.activity = activity;
    this.channel.setMethodCallHandler(this);
  }

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_toast");
    channel.setMethodCallHandler(new FlutterToastPlugin(channel,registrar.activity()));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }else  if(call.method.endsWith("showToast")){
      String msg = call.argument("msg");
      int showTime = call.argument("showTime");
      Toast.makeText(activity,msg,showTime).show();
    }

    else {
      result.notImplemented();
    }
  }
}
