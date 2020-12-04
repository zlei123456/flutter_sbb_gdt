package com.sbb.flutter.flutter_sbb_gdt.gdt;

import android.content.Context;
import android.os.SystemClock;

import com.qq.e.ads.rewardvideo.RewardVideoAD;
import com.qq.e.ads.rewardvideo.RewardVideoADListener;
import com.qq.e.comm.util.AdError;

import java.util.HashMap;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;


public class RewardAD implements RewardVideoADListener{
  private RewardVideoAD rewardVideoAD;
  private boolean adLoaded;//广告加载成功标志
  private boolean videoCached;//视频素材文件下载完成标志
  MethodChannel.Result rewardResult;

  public RewardAD(Context context, String appId, String posId, MethodChannel.Result result) {
    this.rewardResult = result;
    rewardVideoAD = new RewardVideoAD(context, posId,this, true);
    adLoaded = false;
    videoCached = false;
    // 2. 加载激励视频广告
    rewardVideoAD.loadAD();
  }

  public void RewardAD(Context context, String appid, String id, MethodChannel.Result result){

  }

  private void showAd() {
    if(rewardVideoAD!=null){
      if(!rewardVideoAD.hasShown()){
        long delta = 1000;
        if (SystemClock.elapsedRealtime() < (rewardVideoAD.getExpireTimestamp() - delta)) {
          rewardVideoAD.showAD();
        }
      }
    }
  }

  @Override
  public void onADLoad() {
    adLoaded = true;

    showAd();
  }

  @Override
  public void onVideoCached() {

  }

  @Override
  public void onADShow() {

  }

  @Override
  public void onADExpose() {

  }

  @Override
  public void onReward() {
//    RewardModel rewardModel= new RewardModel();
//    listener.onReward(rewardModel);
  }

  @Override
  public void onADClick() {
//    listener.onAdClicked();
  }

  @Override
  public void onVideoComplete() {
//    listener.onVideoPlayFinish();
  }

  @Override
  public void onADClose() {
    HashMap<String, String> rets = new HashMap<>();
    rets.put("rewardAd", "close");
    rewardResult.success(rets);
//    listener.onAdDismiss();
  }

  @Override
  public void onError(AdError adError) {
//    listener.onAdLoadFail(adError.getErrorCode(),adError.getErrorMsg());
  }

}