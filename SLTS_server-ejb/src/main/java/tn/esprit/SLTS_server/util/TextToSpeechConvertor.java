package tn.esprit.SLTS_server.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeechConvertor {

	// Some available voices are (kevin, kevin16, alan)
	private static final String VOICE_NAME_KEVIN = "kevin16";
	private final Voice voice;
	
	public TextToSpeechConvertor() {

		VoiceManager vm = VoiceManager.getInstance();		
		voice = vm.getVoice(VOICE_NAME_KEVIN);
		voice.allocate();
               // System.out.println(vm.getVoiceInfo());
	}

	public void speak(String inputText) {

		if(inputText != null && !inputText.isEmpty()) {
			
			voice.speak(inputText);
		}
	}

}
