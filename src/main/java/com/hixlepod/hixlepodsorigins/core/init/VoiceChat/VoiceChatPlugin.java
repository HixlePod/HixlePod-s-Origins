package com.hixlepod.hixlepodsorigins.core.init.VoiceChat;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;

@Deprecated
public class VoiceChatPlugin implements VoicechatPlugin {

    @Override
    public String getPluginId() {
        return HixlePodsOrigins.MODID;
    }

    @Override
    public void initialize(VoicechatApi api) {

    }
    /*

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(VoicechatServerStartedEvent.class, this::onServerStarted);
    }

    public void onServerStarted(VoicechatServerStartedEvent event) {
        System.out.println(event.getVoicechat());
    }

     */
}
