package com.jwplayer.demo.recyclerview;

import android.view.Window;
import android.view.WindowManager;

import com.jwplayer.pub.api.JWPlayer;
import com.jwplayer.pub.api.events.AdCompleteEvent;
import com.jwplayer.pub.api.events.AdErrorEvent;
import com.jwplayer.pub.api.events.AdPauseEvent;
import com.jwplayer.pub.api.events.AdPlayEvent;
import com.jwplayer.pub.api.events.AdSkippedEvent;
import com.jwplayer.pub.api.events.CompleteEvent;
import com.jwplayer.pub.api.events.ErrorEvent;
import com.jwplayer.pub.api.events.EventType;
import com.jwplayer.pub.api.events.PauseEvent;
import com.jwplayer.pub.api.events.PlayEvent;
import com.jwplayer.pub.api.events.listeners.AdvertisingEvents;
import com.jwplayer.pub.api.events.listeners.VideoPlayerEvents;

/**
 * Sets the FLAG_KEEP_SCREEN_ON flag during playback - disables it when playback is stopped
 *
 * @author Paul Mandal [paul.mandal@jwplayer.com]
 */
public class KeepScreenOnHandler implements VideoPlayerEvents.OnPlayListener,
											VideoPlayerEvents.OnPauseListener,
											VideoPlayerEvents.OnCompleteListener,
											VideoPlayerEvents.OnErrorListener,
											AdvertisingEvents.OnAdPlayListener,
											AdvertisingEvents.OnAdPauseListener,
											AdvertisingEvents.OnAdCompleteListener,
											AdvertisingEvents.OnAdSkippedListener,
											AdvertisingEvents.OnAdErrorListener {

    /**
     * The application window
     */
    private Window mWindow;

    public KeepScreenOnHandler( Window window) {
        mWindow = window;
    }

    public void addListeners(JWPlayer player) {
        player.addListener(EventType.PLAY, this);
        player.addListener(EventType.PAUSE, this);
        player.addListener(EventType.COMPLETE, this);
        player.addListener(EventType.ERROR, this);
        player.addListener(EventType.AD_PLAY, this);
        player.addListener(EventType.AD_PAUSE, this);
        player.addListener(EventType.AD_COMPLETE, this);
        player.addListener(EventType.AD_ERROR, this);
    }

    public void removeListeners(JWPlayer player) {
        player.removeListener(EventType.PLAY, this);
        player.removeListener(EventType.PAUSE, this);
        player.removeListener(EventType.COMPLETE, this);
        player.removeListener(EventType.ERROR, this);
        player.removeListener(EventType.AD_PLAY, this);
        player.removeListener(EventType.AD_PAUSE, this);
        player.removeListener(EventType.AD_COMPLETE, this);
        player.removeListener(EventType.AD_ERROR, this);
    }

    private void updateWakeLock(boolean enable) {
        if (enable) {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    @Override
    public void onPlay(PlayEvent playEvent) {
        updateWakeLock(true);
    }

    @Override
    public void onPause(PauseEvent pauseEvent) {
        updateWakeLock(false);
    }

    @Override
    public void onComplete(CompleteEvent completeEvent) {
        updateWakeLock(false);
    }

    @Override
    public void onError(ErrorEvent errorEvent) {
        updateWakeLock(false);
    }

    @Override
    public void onAdPlay(AdPlayEvent adPlayEvent) {
        updateWakeLock(true);
    }

    @Override
    public void onAdPause(AdPauseEvent adPauseEvent) {
        updateWakeLock(false);
    }

    @Override
    public void onAdComplete(AdCompleteEvent adCompleteEvent) {
        updateWakeLock(false);
    }

    @Override
    public void onAdSkipped(AdSkippedEvent adSkippedEvent) {
        updateWakeLock(false);
    }

    @Override
    public void onAdError(AdErrorEvent adErrorEvent) {
        updateWakeLock(false);
    }
}
