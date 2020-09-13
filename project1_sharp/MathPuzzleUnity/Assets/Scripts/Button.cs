using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Button : MonoBehaviour {

	private Transform localTransform;
	private GameObject mapBeforePlayScreen;
	private GameObject mapPlayScreen;
	private string s = "";
	private float t;

	void Start () {
		localTransform = transform;
	}
	
	void Update () {
		
	}

	void OnMouseDown () {
		if (localTransform.name == "Win_WS_Button") {
			Contr.instance.playScreen.SetActive (true);
			Contr.instance.beforePlayScreen.SetActive (false);
			GenerationBeforePlayScreen (0, Contr.instance.beforePlayScreen, Contr.instance.fullPuzzleSprite, "instance");
			StartCoroutine (Move (Contr.instance.playScreen, Contr.instance.beforePlayScreen, Contr.instance.playScreen.transform.position, -Contr.instance.beforePlayScreen.transform.position, 0.01f, 1.5f));
		}
		if (localTransform.name == "Yes_OpeS_Button") {
			Contr.instance.playScreen.SetActive (true);
			Contr.instance.beforePlayScreen.SetActive (false);
			GenerationBeforePlayScreen (0, Contr.instance.beforePlayScreen, Contr.instance.fullPuzzleSprite, "instance");
			StartCoroutine (Move (Contr.instance.playScreen, Contr.instance.beforePlayScreen, Contr.instance.playScreen.transform.position, -Contr.instance.beforePlayScreen.transform.position, 0.01f, 1.5f));
		}
		if (Contr.instance.MoveGet ()) {
			s = "";
			for (int i = 0; i < 8; i++) {
				s += localTransform.name [i];
			}
			if (s == "instance") {
				GenerationPlayScreen (Contr.instance.StringInInt (localTransform.name));
				Contr.instance.audioSource.Stop();
				Contr.instance.audioSource.clip = Contr.instance.audioGame;
				Contr.instance.audioSource.volume = PlayerPrefs.GetFloat ("MusicGame");
				Contr.instance.audioSource.PlayDelayed(0.75f);
				StartCoroutine (Move (Contr.instance.beforePlayScreen, Contr.instance.playScreen, Contr.instance.beforePlayScreen.transform.position, -Contr.instance.playScreen.transform.position, 0.01f, 1.5f));
			}
			switch (localTransform.name) {
				case "PlusMenu":
					if (PlayerPrefs.GetFloat("MusicMenu") < 0.98f) {
						Volume(Contr.instance.volumeMenu, 1f, Contr.instance.audioMenu); 
					}	
					
					break;
				case "MinusMenu":
					if (PlayerPrefs.GetFloat("MusicMenu") > 0.02f) {
						Volume(Contr.instance.volumeMenu, -1f, Contr.instance.audioMenu);
					}	
					break;
				case "PlusGame":
					if (PlayerPrefs.GetFloat("MusicGame") < 0.98f) {
						Volume(Contr.instance.volumeGame, 1f, Contr.instance.audioGame); 
					}
					break;
				case "MinusGame":
					if (PlayerPrefs.GetFloat("MusicGame") > 0.02f) {
						Volume(Contr.instance.volumeGame, -1f, Contr.instance.audioGame); 
					}
					break;
				case "Back_BPS_Button":
					StartCoroutine (Move (Contr.instance.beforePlayScreen, Contr.instance.menuScreen, Contr.instance.beforePlayScreen.transform.position, -Contr.instance.menuScreen.transform.position, 0.01f, 1.5f));
					break;
				case "Back_HS_Button":
					StartCoroutine (Move (Contr.instance.helpScreen, Contr.instance.menuScreen, Contr.instance.helpScreen.transform.position, -Contr.instance.menuScreen.transform.position, 0.01f, 1.5f));
					break;
				case "Back_OS_Button":
					if (Contr.instance.playScreen.transform.childCount > 6) {
						StartCoroutine (Move (Contr.instance.optionsScreen, Contr.instance.playScreen, Contr.instance.optionsScreen.transform.position, -Contr.instance.playScreen.transform.position, 0.01f, 1.5f));
					} else {
						StartCoroutine (Move (Contr.instance.optionsScreen, Contr.instance.menuScreen, Contr.instance.optionsScreen.transform.position, -Contr.instance.menuScreen.transform.position, 0.01f, 1.5f));
					}
					break;
				case "Back_PS_Button":
					Contr.instance.GameSet (false);
					GenerationBeforePlayScreen (0, Contr.instance.beforePlayScreen, Contr.instance.fullPuzzleSprite, "instance");
					Contr.instance.audioSource.Stop();
					Contr.instance.audioSource.clip = Contr.instance.audioMenu;
					Contr.instance.audioSource.volume = PlayerPrefs.GetFloat ("MusicMenu");
					Contr.instance.audioSource.PlayDelayed(0.75f);
					StartCoroutine (Move (Contr.instance.playScreen, Contr.instance.beforePlayScreen, Contr.instance.playScreen.transform.position, -Contr.instance.beforePlayScreen.transform.position, 0.01f, 1.5f));
					break;
				case "English_LS_Button":
					Language (0);
					break;
				case "English_OS_Button":
					RadioButton (0);
					break;	
				case "Help_MS_Button":
					StartCoroutine (Move (Contr.instance.menuScreen, Contr.instance.helpScreen, Contr.instance.menuScreen.transform.position, -Contr.instance.helpScreen.transform.position, 0.01f, 1.5f));
					break;
				case "Japanese_LS_Button":
					Language (2);
					break;
				case "Japanese_OS_Button":
					RadioButton (2);
					break;
				case "Left_BPS_Button":
					GenerationBeforePlayScreen ((Contr.instance.fullPuzzleSprite.Length + 8) / 9 - 1, Contr.instance.beforePlayScreen, Contr.instance.fullPuzzleSprite, "instance");
					break;
				
				case "No_QS_Button":
					StartCoroutine (Move (Contr.instance.quitScreen, Contr.instance.menuScreen, Contr.instance.quitScreen.transform.position, -Contr.instance.menuScreen.transform.position, 0.01f, 1.5f));
					break;
				case "Options_PS_Button":
					Contr.instance.GameSet (false);
					StartCoroutine (Move (Contr.instance.playScreen, Contr.instance.optionsScreen, Contr.instance.playScreen.transform.position, -Contr.instance.optionsScreen.transform.position, 0.01f, 1.5f));
					break;
				case "Options_MS_Button":
					StartCoroutine (Move (Contr.instance.menuScreen, Contr.instance.optionsScreen, Contr.instance.menuScreen.transform.position, -Contr.instance.optionsScreen.transform.position, 0.01f, 1.5f));
					break;
				case "Play_MS_Button":
					GenerationBeforePlayScreen (0, Contr.instance.beforePlayScreen, Contr.instance.fullPuzzleSprite, "instance");
					StartCoroutine (Move (Contr.instance.menuScreen, Contr.instance.beforePlayScreen, Contr.instance.menuScreen.transform.position, -Contr.instance.beforePlayScreen.transform.position, 0.01f, 1.5f));
					break;
				case "Quit_MS_Button":
					StartCoroutine (Move (Contr.instance.menuScreen, Contr.instance.quitScreen, Contr.instance.menuScreen.transform.position, -Contr.instance.quitScreen.transform.position, 0.01f, 1.5f));
					break;
				case "Right_BPS_Button":
					GenerationBeforePlayScreen (1, Contr.instance.beforePlayScreen, Contr.instance.fullPuzzleSprite, "instance");
					break;
				case "Russian_LS_Button":
					Language (1);
					break;
				case "Russian_OS_Button":
					RadioButton (1);
					break;
				case "Yes_AQS_Button":
					Application.Quit();
					break;
				case "Yes_QS_Button":
					StartCoroutine (Move (Contr.instance.quitScreen, Contr.instance.afterQuitScreen, Contr.instance.quitScreen.transform.position,  -Contr.instance.afterQuitScreen.transform.position, 0.01f, 1.5f));
					break;
			}
		}	
	}

	void Volume(GameObject point, float direction, AudioClip audio) {
		point.transform.localPosition = new Vector2(point.transform.localPosition.x + direction*0.05f*6.66f, point.transform.localPosition.y);
		PlayerPrefs.SetFloat(point.transform.name, PlayerPrefs.GetFloat(point.transform.name) + direction*0.05f);
		if (Contr.instance.audioSource.clip == audio) {
			Contr.instance.audioSource.volume = PlayerPrefs.GetFloat(point.transform.name);
		}
	}

	void GenerationBeforePlayScreen (int q, GameObject screen, Sprite [] sprites, string name) { 
		Contr.instance.NumberBPSSet ((Contr.instance.NumberBPSGet () + q) % ((sprites.Length + 8) / 9));
		if ((localTransform.name == "Right_BPS_Button")||(localTransform.name == "Right_ES_Button")) {
			Destroy (screen.transform.GetChild(6).gameObject);
		}
		if ((localTransform.name == "Left_BPS_Button")||(localTransform.name == "Left_ES_Button")) {
			Destroy (screen.transform.GetChild(6).gameObject);
		}
		float a = -3f;
		float b = 5f;
		mapBeforePlayScreen = new GameObject ();
		Transform mapTransform = mapBeforePlayScreen.transform;
		mapTransform.parent = screen.transform;
		mapTransform.localPosition = new Vector2 (0, 0);
		mapTransform.localScale = new Vector2 (1, 1);

		if (name != "avatarus") {

			for (int i = 0; i < 9; i ++) {
				Contr.instance.PrefabInstanceSet (Instantiate (Contr.instance.prefab, new Vector2 (0f, 0f), Quaternion.identity) as GameObject);
				Transform instanceTransform = Contr.instance.PrefabInstanceGet ().transform;
				instanceTransform.parent = mapTransform;
				instanceTransform.localScale = new Vector2 (0.25f, 0.25f);
				instanceTransform.localPosition = new Vector2 (a + ((i % 3) * 3), b - ((i / 3) * 3));
				instanceTransform.name = name + (i + 9 * Contr.instance.NumberBPSGet()).ToString ();
				Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sprite = sprites [i + 9 * Contr.instance.NumberBPSGet()];
				if (PlayerPrefs.GetInt ("Field") > i + 9 * Contr.instance.NumberBPSGet()) {
					Contr.instance.PrefabInstanceSet (Instantiate (Contr.instance.prefab, new Vector2 (0f, 0f), Quaternion.identity) as GameObject);
					instanceTransform = Contr.instance.PrefabInstanceGet ().transform;
					instanceTransform.parent = mapBeforePlayScreen.transform.GetChild (i).transform;
					Destroy (Contr.instance.PrefabInstanceGet ().GetComponent <Button>());
					Destroy (Contr.instance.PrefabInstanceGet ().GetComponent <BoxCollider2D>());
					instanceTransform.localScale = new Vector2 (1f, 1f);
					instanceTransform.localPosition = new Vector2 (0f, 0f);
					instanceTransform.name = "checkMark" + i.ToString ();
					Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sprite = Contr.instance.checkMark;
					Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sortingOrder = 1;
				} else if (i + 9 * Contr.instance.NumberBPSGet() > PlayerPrefs.GetInt ("Field")) {
					Contr.instance.PrefabInstanceSet (Instantiate (Contr.instance.prefab, new Vector2 (0f, 0f), Quaternion.identity) as GameObject);
					instanceTransform = Contr.instance.PrefabInstanceGet ().transform;
					instanceTransform.parent = mapBeforePlayScreen.transform.GetChild (i).transform;
					Destroy (mapBeforePlayScreen.transform.GetChild (i).GetComponent <Button>());
					Destroy (Contr.instance.PrefabInstanceGet ().GetComponent <Button>());
					instanceTransform.localScale = new Vector2 (1f, 1f);
					instanceTransform.localPosition = new Vector2 (0f, 0f);
					instanceTransform.name = "lock" + i.ToString ();
					Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sprite = Contr.instance.locked;
					Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sortingOrder = 1;
				} else {
					Contr.instance.PrefabInstanceSet (Instantiate (Contr.instance.prefab, new Vector2 (0f, 0f), Quaternion.identity) as GameObject);
					instanceTransform = Contr.instance.PrefabInstanceGet ().transform;
					instanceTransform.parent = mapBeforePlayScreen.transform.GetChild (i).transform;
					Destroy (Contr.instance.PrefabInstanceGet ().GetComponent <BoxCollider2D>());
					Destroy (Contr.instance.PrefabInstanceGet ().GetComponent <Button>());
					instanceTransform.localScale = new Vector2 (1f, 1f);
					instanceTransform.localPosition = new Vector2 (0f, 0f);
					instanceTransform.name = "go" + i.ToString ();
					Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sprite = Contr.instance.go;
					Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sortingOrder = 1;
				}
			}

		} else {

			for (int i = 0; i < 9; i ++) {
				Contr.instance.PrefabInstanceSet (Instantiate (Contr.instance.prefab, new Vector2 (0f, 0f), Quaternion.identity) as GameObject);
				Transform instanceTransform = Contr.instance.PrefabInstanceGet ().transform;
				instanceTransform.parent = mapTransform;
				instanceTransform.localScale = new Vector2 (0.25f, 0.25f);
				instanceTransform.localPosition = new Vector2 (a + ((i % 3) * 3), b - ((i / 3) * 3));
				instanceTransform.name = name + (i + 9 * Contr.instance.NumberBPSGet()).ToString ();
				Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sprite = sprites [i + 9 * Contr.instance.NumberBPSGet()];
				if (PlayerPrefs.GetInt ("Field") > i + 9 * Contr.instance.NumberBPSGet()) {
					Contr.instance.PrefabInstanceSet (Instantiate (Contr.instance.prefab, new Vector2 (0f, 0f), Quaternion.identity) as GameObject);
					instanceTransform = Contr.instance.PrefabInstanceGet ().transform;
					instanceTransform.parent = mapBeforePlayScreen.transform.GetChild (i).transform;
					Destroy (Contr.instance.PrefabInstanceGet ().GetComponent <BoxCollider2D>());
					Destroy (Contr.instance.PrefabInstanceGet ().GetComponent <Button>());
					instanceTransform.localScale = new Vector2 (1f, 1f);
					instanceTransform.localPosition = new Vector2 (0f, 0f);
					instanceTransform.name = "go" + i.ToString ();
					Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sprite = Contr.instance.go;
					Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sortingOrder = 1;
				} else if (i + 9 * Contr.instance.NumberBPSGet() >= PlayerPrefs.GetInt ("Field")) {
					Contr.instance.PrefabInstanceSet (Instantiate (Contr.instance.prefab, new Vector2 (0f, 0f), Quaternion.identity) as GameObject);
					instanceTransform = Contr.instance.PrefabInstanceGet ().transform;
					instanceTransform.parent = mapBeforePlayScreen.transform.GetChild (i).transform;
					Destroy (mapBeforePlayScreen.transform.GetChild (i).GetComponent <Button>());
					Destroy (Contr.instance.PrefabInstanceGet ().GetComponent <Button>());
					instanceTransform.localScale = new Vector2 (1f, 1f);
					instanceTransform.localPosition = new Vector2 (0f, 0f);
					instanceTransform.name = "lock" + i.ToString ();
					Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sprite = Contr.instance.locked;
					Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sortingOrder = 1;
				}
			}
		}
	}

	void GenerationPlayScreen (int c) {
		Contr.instance.FieldSet (c);
		float a = -3.84f;
		float b = 3.84f;
		bool flag = false;
		int d = 0;
		mapPlayScreen = new GameObject ();
		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j < 4; j ++) {
				flag = false;
				while (!flag) {
					flag = true;
					d = Random.Range (0, 16);
					for (int i1 = 0; i1 < 15; i1 ++) {
						if (d == Contr.instance.NumbersGet () [i1]) {
							flag = false;
						}
					}
				}
				Contr.instance.NumbersSet (i * 4 + j, d);
			}
		}
		int summInversion = 0;
		int emptyPart = -1;
		for (int i = 0; i < 16; i++) {
			if (Contr.instance.NumbersGet ()[i] == 15) {
				emptyPart = i;
				continue;
			}
			for (int j = i; j < 16; j++) {
				if (Contr.instance.NumbersGet ()[i] > Contr.instance.NumbersGet ()[j]) {
					summInversion++;
				}
			}
		}
		if ((summInversion + ((emptyPart) / 4 + 1)) % 2 != 0) {
			if (Contr.instance.NumbersGet ()[0] == 15) {
				Contr.instance.NumbersSet (1, Contr.instance.NumbersGet ()[1] + Contr.instance.NumbersGet ()[2]);
				Contr.instance.NumbersSet (2, Contr.instance.NumbersGet ()[1] - Contr.instance.NumbersGet ()[2]);
				Contr.instance.NumbersSet (1, Contr.instance.NumbersGet ()[1] - Contr.instance.NumbersGet ()[2]);
			}
			if (Contr.instance.NumbersGet ()[1] == 15) {
				Contr.instance.NumbersSet (0, Contr.instance.NumbersGet ()[0] + Contr.instance.NumbersGet ()[2]);
				Contr.instance.NumbersSet (2, Contr.instance.NumbersGet ()[0] - Contr.instance.NumbersGet ()[2]);
				Contr.instance.NumbersSet (0, Contr.instance.NumbersGet ()[0] - Contr.instance.NumbersGet ()[2]);
			} else {
				Contr.instance.NumbersSet (0, Contr.instance.NumbersGet ()[0] + Contr.instance.NumbersGet ()[1]);
				Contr.instance.NumbersSet (1, Contr.instance.NumbersGet ()[0] - Contr.instance.NumbersGet ()[1]);
				Contr.instance.NumbersSet (0, Contr.instance.NumbersGet ()[0] - Contr.instance.NumbersGet ()[1]);
			}
		}
		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j < 4; j ++) {
				Contr.instance.PartPosSet (i, j, Contr.instance.NumbersGet ()[i * 4 + j]);
			}
		}
		


		Transform mapTransform = mapPlayScreen.transform;
		Transform instanceTransform;
		mapTransform.parent = Contr.instance.playScreen.transform;
		mapTransform.localPosition = new Vector2 (0, 0);
		mapTransform.localScale = new Vector2 (1, 1);

		for (int i = 0; i < 16; i ++) {
			if (Contr.instance.NumbersGet () [i] == 15) {
				continue;
			}
			Contr.instance.PrefabInstanceSet (Instantiate (Contr.instance.prefab, new Vector2 (0, 0), Quaternion.identity) as GameObject);
			Destroy(Contr.instance.PrefabInstanceGet ().GetComponent <Button>());
			Contr.instance.PrefabInstanceGet ().AddComponent <Part>();
			instanceTransform = Contr.instance.PrefabInstanceGet ().transform;
			instanceTransform.parent = mapTransform;
			instanceTransform.localScale = new Vector2 (1f, 1f);
			instanceTransform.localPosition = new Vector2 (a + ((i % 4) * 2.56f), b - ((i / 4) * 2.56f));
			instanceTransform.name = "part" + (i / 4).ToString () + (i % 4).ToString ();
			Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sprite = Contr.instance.Part2DSpriteGet () [c, Contr.instance.NumbersGet () [i]];
			Vector2 sizePref = Contr.instance.PrefabInstanceGet ().GetComponent <BoxCollider2D>().size;
			sizePref.x /= 4f; 
			sizePref.y /= 4f; 
			Contr.instance.PrefabInstanceGet ().GetComponent <BoxCollider2D>().size = sizePref;
		}
		for (int i = 0; i < 16; i ++) {
			Contr.instance.NumbersSet (i, -1);
		}
		Contr.instance.PrefabInstanceSet (Instantiate (Contr.instance.prefab, new Vector2 (0f, 0f), Quaternion.identity) as GameObject);
		Destroy(Contr.instance.PrefabInstanceGet ().GetComponent <Button>());
	    instanceTransform = Contr.instance.PrefabInstanceGet ().transform;
		instanceTransform.parent = Contr.instance.playScreen.transform;
		instanceTransform.localScale = new Vector2 (0.375f, 0.375f);
		instanceTransform.localPosition = new Vector2 (-2.7f, 7.4f);
		instanceTransform.name = "miniMap";
		Contr.instance.PrefabInstanceGet ().GetComponent<SpriteRenderer>().sprite = Contr.instance.fullPuzzleSprite [c];
	}

	void Language (int a) {
		Contr.instance.LanguageNumberSet (a);
		for (int i = 0; i < Contr.instance.textUI.Length; i++) {
			if ((Contr.instance.textUI [i].font == Contr.instance.contentFont) && (Contr.instance.LanguageNumberGet () == 2)) {
				Contr.instance.textUI [i].font = Contr.instance.contentFontJ;
				if (Contr.instance.textUI [i].fontSize == 110) {
					Contr.instance.textUI [i].fontSize = 160;
				} else if (Contr.instance.textUI [i].fontSize == 170) {
					Contr.instance.textUI [i].fontSize = 220;
				}
			} else if ((Contr.instance.textUI [i].font == Contr.instance.contentFontJ) && (Contr.instance.LanguageNumberGet () != 2)) {
				Contr.instance.textUI [i].font = Contr.instance.contentFont;
				if (Contr.instance.textUI [i].fontSize == 160) {
					Contr.instance.textUI [i].fontSize = 110;
				} else if (Contr.instance.textUI [i].fontSize == 220) {
					Contr.instance.textUI [i].fontSize = 170;
				}
			}
			if ((Contr.instance.textUI [i].font == Contr.instance.buttonFont) && (Contr.instance.LanguageNumberGet () == 2)) {
				Contr.instance.textUI [i].font = Contr.instance.buttonFontJ;
				Contr.instance.textUI [i].fontSize = 120;
			} else if ((Contr.instance.textUI [i].font == Contr.instance.buttonFontJ) && (Contr.instance.LanguageNumberGet () != 2)) {
				Contr.instance.textUI [i].font = Contr.instance.buttonFont;
				Contr.instance.textUI [i].fontSize = 80;
			}
			Contr.instance.textUI [i].text = Contr.instance.TextTranslateGet () [a, i];
		}
		Contr.instance.checkSprite [a].SetActive (true);

		Contr.instance.audioSource.PlayDelayed(0.75f);
		StartCoroutine (Move (Contr.instance.languageScreen, Contr.instance.menuScreen, Contr.instance.languageScreen.transform.position, -Contr.instance.menuScreen.transform.position, 0.01f, 1.5f));
	}

	IEnumerator Move (GameObject go, GameObject go2, Vector3 cord, Vector3 cordEnd, float value, float speed) {
		t = 0;
		Contr.instance.MoveSet (false);
		while (t < 1) {
			t += Time.deltaTime * speed;
			yield return new WaitForSeconds (value);
			go2.transform.position = Vector3.Lerp (-cordEnd, cord, t);
			go.transform.position = Vector3.Lerp (cord, cordEnd, t);
		}
		if (localTransform.name == "Back_PS_Button") {
			Destroy (localTransform.parent.gameObject.transform.GetChild(6).gameObject);
			Destroy (localTransform.parent.gameObject.transform.GetChild(7).gameObject);
			Destroy (Contr.instance.beforePlayScreen.transform.GetChild(6).gameObject);
		}


		if (localTransform.name == "Yes_OpeS_Button") {
			Contr.instance.beforePlayScreen.SetActive (true);
			Destroy (Contr.instance.playScreen.transform.GetChild(6).gameObject);
			Destroy (Contr.instance.playScreen.transform.GetChild(7).gameObject);
			Destroy (Contr.instance.beforePlayScreen.transform.GetChild(6).gameObject);
		}
		if (localTransform.name == "Win_WS_Button") {
			Contr.instance.audioSource.Stop();
		    Contr.instance.audioSource.clip = Contr.instance.audioMenu;
		    Contr.instance.audioSource.volume = PlayerPrefs.GetFloat ("MusicMenu");
			Contr.instance.audioSource.PlayDelayed(0.75f);
			Contr.instance.winScreen.SetActive (false);
			Contr.instance.beforePlayScreen.SetActive (true);
			Destroy (Contr.instance.playScreen.transform.GetChild(6).gameObject);
			Destroy (Contr.instance.playScreen.transform.GetChild(7).gameObject);
			Destroy (Contr.instance.beforePlayScreen.transform.GetChild(6).gameObject);
		}
		if (localTransform.name == "Back_BPS_Button") {
			Destroy (Contr.instance.beforePlayScreen.transform.GetChild(6).gameObject);
		}

		Contr.instance.MoveSet (true);
	}

	void RadioButton (int a) {
		if (!transform.GetChild(0).gameObject.activeSelf) {
			for (int i = 0; i < 3; i++) {
				if (Contr.instance.checkSprite [i].activeSelf) {
					Contr.instance.checkSprite [i].SetActive (false);
				}
			}
			Contr.instance.LanguageNumberSet (a);
			for (int j = 0; j < Contr.instance.textUI.Length; j++) {
				if ((Contr.instance.textUI [j].font == Contr.instance.contentFont) && (Contr.instance.LanguageNumberGet () == 2)) {
					Contr.instance.textUI [j].font = Contr.instance.contentFontJ;
					if (Contr.instance.textUI [j].fontSize == 110) {
						Contr.instance.textUI [j].fontSize = 160;
					} else if (Contr.instance.textUI [j].fontSize == 170) {
						Contr.instance.textUI [j].fontSize = 220;
					}
				} else if ((Contr.instance.textUI [j].font == Contr.instance.contentFontJ) && (Contr.instance.LanguageNumberGet () != 2)) {
					Contr.instance.textUI [j].font = Contr.instance.contentFont;
					if (Contr.instance.textUI [j].fontSize == 160) {
					Contr.instance.textUI [j].fontSize = 110;
					} else if (Contr.instance.textUI [j].fontSize == 220) {
						Contr.instance.textUI [j].fontSize = 170;
					}
				}
				if ((Contr.instance.textUI [j].font == Contr.instance.buttonFont) && (Contr.instance.LanguageNumberGet () == 2)) {
					Contr.instance.textUI [j].font = Contr.instance.buttonFontJ;
					Contr.instance.textUI [j].fontSize = 120;
				} else if ((Contr.instance.textUI [j].font == Contr.instance.buttonFontJ) && (Contr.instance.LanguageNumberGet () != 2)) {
					Contr.instance.textUI [j].font = Contr.instance.buttonFont;
					Contr.instance.textUI [j].fontSize = 80;
				}
				Contr.instance.textUI [j].text = Contr.instance.TextTranslateGet () [a, j];
			}
			transform.GetChild(0).gameObject.SetActive(!transform.GetChild(0).gameObject.activeSelf);
		}
	}

}
