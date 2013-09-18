/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.googlecode.tcime.unofficial;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

/**
 * Zhuyin input method.
 */
public class ZhuyinIME extends AbstractIME {
	private HashMap<Integer, Integer> keyMapping;
	private SharedPreferences preferences;
	private boolean isAltUsed = false;

	@Override
	protected KeyboardSwitch createKeyboardSwitch(Context context) {
		return new KeyboardSwitch(context, R.xml.zhuyin);
	}

	@Override
	protected Editor createEditor() {
		return new ZhuyinEditor();
	}

	@Override
	protected WordDictionary createWordDictionary(Context context) {
		return new ZhuyinDictionary(context);
	}

	@Override
	public void onCreate() {
		/*
		 * keyMapping.put(8, 0x3105);// ㄅ keyMapping.put(45, 0x3106);// ㄆ
		 * keyMapping.put(29, 0x3107);// ㄇ keyMapping.put(54, 0x3108);// ㄈ
		 * keyMapping.put(9, 0x3109);// ㄉ keyMapping.put(51, 0x310A);// ㄊ
		 * keyMapping.put(47, 0x310B);// ㄋ keyMapping.put(52, 0x310C);// ㄌ
		 * keyMapping.put(33, 0x310D);// ㄍ keyMapping.put(32, 0x310E);// ㄎ
		 * keyMapping.put(31, 0x310F);// ㄏ keyMapping.put(46, 0x3110);// ㄐ
		 * keyMapping.put(34, 0x3111);// ㄑ keyMapping.put(50, 0x3112);// ㄒ
		 * keyMapping.put(12, 0x3113);// ㄓ keyMapping.put(48, 0x3114);// ㄔ
		 * keyMapping.put(35, 0x3115);// ㄕ keyMapping.put(30, 0x3116);// ㄖ
		 * keyMapping.put(53, 0x3117);// ㄗ keyMapping.put(36, 0x3118);// ㄘ
		 * keyMapping.put(42, 0x3119);// ㄙ keyMapping.put(15, 0x311A);// ㄚ
		 * keyMapping.put(37, 0x311B);// ㄛ keyMapping.put(39, 0x311C);// ㄜ
		 * keyMapping.put(55, 0x311D);// ㄝ keyMapping.put(16, 0x311E);// ㄞ
		 * keyMapping.put(43, 0x311F);// ㄟ keyMapping.put(40, 0x3120);// ㄠ
		 * keyMapping.put(56, 0x3121);// ㄡ keyMapping.put(7, 0x3122);// ㄢ
		 * keyMapping.put(44, 0x3123);// ㄣ keyMapping.put(74, 0x3124);// ㄤ
		 * keyMapping.put(76, 0x3125);// ㄥ keyMapping.put(69, 0x3126);// ㄦ
		 * keyMapping.put(49, 0x3127);// ㄧ keyMapping.put(38, 0x3128);// ㄨ
		 * keyMapping.put(41, 0x3129);// ㄩ keyMapping.put(10, 0x2C7);// ˇ
		 * keyMapping.put(11, 0x2CB);// ˋ keyMapping.put(13, 0x2CA);// ˊ
		 * keyMapping.put(14, 0x2D9);// ˙
		 */
		super.onCreate();
		keyMapping = new HashMap<Integer, Integer>();
		// line 1
		keyMapping.put(8, 0x0031);// 1
		keyMapping.put(9, 0x0032);// 2
		keyMapping.put(10, 0x0033);// 3
		keyMapping.put(11, 0x0034);// 4
		keyMapping.put(12, 0x0035);// 5
		keyMapping.put(13, 0x0036);// 6
		keyMapping.put(14, 0x0037);// 7
		keyMapping.put(15, 0x0038);// 8
		keyMapping.put(16, 0x0039);// 9
		keyMapping.put(7, 0x0030);// 10

		// line 2
		keyMapping.put(45, 0x3105);// ㄅ
		keyMapping.put(45 + 57, 0x3106);// ㄆ

		keyMapping.put(51, 0x3109);// ㄉ

		keyMapping.put(33, 0x2C7);// ˇ

		keyMapping.put(46, 0x2CB);// ˋ

		keyMapping.put(48, 0x3113);// ˊ

		keyMapping.put(53, 0x2CA);// ˊ

		keyMapping.put(49, 0x2D9);// ˙

		keyMapping.put(37, 0x311A);// ㄚ
		keyMapping.put(37 + 57, 0x311B);// ㄛ

		keyMapping.put(43, 0x311E);// ㄞ
		keyMapping.put(43 + 57, 0x311F);// ㄟ

		keyMapping.put(44, 0x3126);// ㄦ

		// line 3
		keyMapping.put(29, 0x3107);// ㄇ
		keyMapping.put(29 + 57, 0x3108);// ㄈ

		keyMapping.put(47, 0x310A);// ㄊ

		keyMapping.put(32, 0x310D);// ㄍ

		keyMapping.put(34, 0x3110);// ㄐ

		keyMapping.put(35, 0x3114);// ㄔ

		keyMapping.put(36, 0x3117);// ㄗ
		keyMapping.put(36 + 57, 0x3118);// ㄘ

		keyMapping.put(38, 0x3127);// ㄧ
		keyMapping.put(38 + 57, 0x3128);// ㄨ

		keyMapping.put(39, 0x311C);// ㄜ
		keyMapping.put(39 + 57, 0x311D);// ㄝ

		keyMapping.put(40, 0x3120);// ㄠ
		keyMapping.put(40 + 57, 0x3121);// ㄡ

		// line 4
		keyMapping.put(54, 0x310B);// ㄋ
		keyMapping.put(54 + 57, 0x310C);// ㄌ

		keyMapping.put(52, 0x310E);// ㄎ
		keyMapping.put(52 + 57, 0x310F);// ㄏ

		keyMapping.put(31, 0x3111);// ㄑ
		keyMapping.put(31 + 57, 0x3112);// ㄒ

		keyMapping.put(50, 0x3115);// ㄕ
		keyMapping.put(50 + 57, 0x3116);// ㄖ

		keyMapping.put(30, 0x3119);// ㄙ

		keyMapping.put(42, 0x3129);// ㄩ

		keyMapping.put(41, 0x3122);// ㄢ
		keyMapping.put(41 + 57, 0x3123);// ㄣ

		keyMapping.put(56, 0x3124);// ㄤ
		keyMapping.put(56 + 57, 0x3125);// ㄥ
	}

	public void onStartInput(EditorInfo attribute, boolean restarting) {
		super.onStartInput(attribute, restarting);
		showStatusIcon(keyboardSwitch.getLanguageIcon());
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		showStatusIcon(keyboardSwitch.getLanguageIcon());
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (handleLanguageChange(keyCode, event) && keyCode == 64) {
			isAltUsed = false; // Clear Alt status
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Capture the hardware keyboard
		if (hasHardKeyboard) {
			// Check the status
			SoftKeyboard sKB = (SoftKeyboard) keyboardSwitch
					.getCurrentKeyboard();
			if (!checkHardKeyboardAvailable(sKB)) {
				return super.onKeyDown(keyCode, event);
			}

			// Shift + Space
			if (handleLanguageChange(keyCode, event)) {
				isAltUsed = false; // Clear Alt status
				return true;
			}

			// Handle HardKB event on Chinese mode only
			if (sKB.isChinese()) {
				// Milestone first row key
				// (If alt is pressed before, emulate 1 - 0 keys)
				if (isAltUsed || event.isAltPressed()) {
					boolean isTriggered = false;
					switch (keyCode) {
					case KeyEvent.KEYCODE_Q:// q(45)ㄅㄆ
						keyCode = 45 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_I:// i(37)ㄚㄛ
						keyCode = 37 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_O:// o(43)ㄞㄟ
						keyCode = 43 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_A:// a(29)ㄇㄈ
						keyCode = 29 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_H:// h(36)ㄗㄘ
						keyCode = 36 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_J:// j(38)ㄧㄨ
						keyCode = 38 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_K:// k(39)ㄜㄝ
						keyCode = 39 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_L:// l(40)ㄠㄡ
						keyCode = 40 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_Z:// z(54)ㄋㄌ
						keyCode = 54 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_X:// x(52)ㄎㄏ
						keyCode = 52 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_C:// c(31)ㄑㄒ
						keyCode = 31 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_V:// v(50)ㄕㄖ
						keyCode = 50 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_M:// m(41)ㄢㄣ
						keyCode = 41 + 57;
						isTriggered = true;
						break;
					case KeyEvent.KEYCODE_PERIOD: // (56)ㄤㄥ
						keyCode = 56 + 57;
						isTriggered = true;
						break;
					/*
					 * case KeyEvent.KEYCODE_V: // MS1/2 fix (Alt + V = - = ㄦ)
					 * keyCode = KeyEvent.KEYCODE_MINUS; isTriggered = true;
					 * break; case KeyEvent.KEYCODE_COMMA: // MS2 fix (Alt + , =
					 * ; = ㄤ) //case KeyEvent.KEYCODE_L: // MS3 fix (Alt + L =
					 * ㄤ) keyCode = KeyEvent.KEYCODE_SEMICOLON; isTriggered =
					 * true; break; case KeyEvent.KEYCODE_PERIOD: // MS3 fix
					 * (Alt + . = ㄥ) keyCode = KeyEvent.KEYCODE_SLASH;
					 * isTriggered = true; break;
					 */
					}
					if (isTriggered) {
						clearKeyboardMetaState();
						isAltUsed = false;
					} else {
						// Pressed Alt key only
						// Record if Alt key was pressed before
						isAltUsed = true;
						return true;
					}
				}

				// Simulate soft keyboard press
				if (keyMapping.containsKey(keyCode)) {
					onKey(keyMapping.get(keyCode), null);
					return true;
				}
				// Handle Delete
				if (keyCode == KeyEvent.KEYCODE_DEL) {
					onKey(SoftKeyboard.KEYCODE_DELETE, null);
					return true;
				}
				// Handle Space
				if (keyCode == KeyEvent.KEYCODE_SPACE) {
					onKey(SoftKeyboard.KEYCODE_SPACE, null);
					return true;
				}
				// Handle Enter
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					onKey(SoftKeyboard.KEYCODE_ENTER, null);
					return true;
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
