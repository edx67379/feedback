using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Contr : MonoBehaviour {
	public static Contr instance = null;

	private int field;
	private int languageNumber = 0;
	private int [] numbers = new int [16];
	private int numberBPS = 0;
	private Sprite [,] part2DSprite = new Sprite [9,15];
	private int [,] partPos = new int [4,4];
	private GameObject prefabInstance;
	private string [,] textTranslate = new string [3,14];
	private bool win;
	private bool move = true;
	private bool game = false;

	public GameObject afterQuitScreen;
	public GameObject beforePlayScreen;
	public GameObject helpScreen;
	public GameObject languageScreen;
	public GameObject menuScreen;
	public GameObject optionsScreen;
	public GameObject playScreen;
	public GameObject prefab;
	public GameObject quitScreen;
	public GameObject winScreen;

	public Font contentFont;
	public Font contentFontJ;
	public Font buttonFont;
	public Font buttonFontJ;

	public Sprite checkMark;
	public Sprite go;
	public Sprite locked;

	public GameObject [] checkSprite = new GameObject [3];
	public Sprite [] fullPuzzleSprite = new Sprite [9];
	public Sprite [] partSprite = new Sprite [135];
	public Text [] textUI = new Text [14];

	public AudioSource audioSource;
	public AudioClip audioMenu;
	public AudioClip audioGame;

	public GameObject volumeMenu;
	public GameObject volumeGame;

	void Start () {
		if (instance == null){
	        instance = this;
	    } else if (instance != this) {
			Destroy(gameObject);
		}

		textTranslate [0,0] = "Play";
		textTranslate [0,1] = "Help";
		textTranslate [0,2] = "Options";
		textTranslate [0,3] = "Exit";
		textTranslate [0,4] = "Quit?";
		textTranslate [0,5] = "Thank you for playing our game!!!";
		textTranslate [0,6] = "Help";
		textTranslate [0,7] = "Options";
		textTranslate [0,8] = "You have won! New level was opened.";
		textTranslate [0,9] = "Choice level";
		textTranslate [0,10] = "Volume Menu Music";
		textTranslate [0,11] = "Volume Game Music";
		textTranslate [0,12] = "The 15-puzzle is a sliding puzzle that consists of a frame of numbered square tiles in random order with one tile missing. If the size is 4×4 tiles, the puzzle is called the 15-puzzle or 16-puzzle named. The object of the puzzle is to place the tiles in order by making sliding moves that use the empty space.";
		textTranslate [0,13] = "Language";

		textTranslate [1,0] = "Играть";
		textTranslate [1,1] = "Помощь";
		textTranslate [1,2] = "Настройки";
		textTranslate [1,3] = "Выход";
		textTranslate [1,4] = "Выйти?";
		textTranslate [1,5] = "Спасибо, что играете в нашу игру";
		textTranslate [1,6] = "Помощь";
		textTranslate [1,7] = "Настройки";
		textTranslate [1,8] = "Вы победили! Открыт новый уровень.";
		textTranslate [1,9] = "Выбор уровня";
		textTranslate [1,10] = "Музыка в меню";
		textTranslate [1,11] = "Музыка в игре";
		textTranslate [1,12] = "Пятнашки - известная всему миру головоломка. Игроку доступно поле размером 4x4, состоящее из 16 клеток. Все клетки кроме одной заняты костяшками с номерами от 1 до 15, которые перемешаны между собой. Цель игры - расположить костяшки по порядку используя свободное поле.";
		textTranslate [1,13] = "Язык";

		textTranslate [2,0] = "プレイ";
		textTranslate [2,1] = "ヘルプ";
		textTranslate [2,2] = "設定";
		textTranslate [2,3] = "終了";
		textTranslate [2,4] = "止めますか？";
		textTranslate [2,5] = "ゲームを遊んでくれてありがとう!!!";
		textTranslate [2,6] = "ヘルプ";
		textTranslate [2,7] = "設定";
		textTranslate [2,8] = "あなたは勝った！次のレベルが\n開かれた";
		textTranslate [2,9] = "レヴェルを\n選んで";
		textTranslate [2,10] = "音量メニューの音量";
		textTranslate [2,11] = "音量ゲームの音量";
		textTranslate [2,12] = "15パズルは、1つのタイルが欠落しているランダムな順序で番号が付けられた正方形タイルのフレームで構成されるスライドパズルです。 サイズが4×4タイルの場合、パズルは15パズルまたは16パズルと呼ばれます。 パズルの目的は、空のスペースを使用するスライド移動を行うことによって、タイルを順番に配置することです。";
		textTranslate [2,13] = "言語";


		SpriteIn2D (partSprite);
		//PlayerPrefs.SetInt ("Field", 0);
		//PlayerPrefs.SetFloat ("MusicMenu", 0.5f);
		//PlayerPrefs.SetFloat ("MusicGame", 0.5f);
		volumeMenu.transform.localPosition = new Vector2(-3.33f + 6.66f*PlayerPrefs.GetFloat("MusicMenu"), volumeGame.transform.localPosition.y);
		volumeGame.transform.localPosition = new Vector2(-3.33f + 6.66f*PlayerPrefs.GetFloat("MusicGame"), volumeGame.transform.localPosition.y);
		audioSource = GetComponent<AudioSource>();
		audioSource.volume = PlayerPrefs.GetFloat ("MusicMenu");
	}


	void Update () {
		
	}

	public int FieldGet () {
		return field;
	}
	public void FieldSet (int a) {
		field = a;
	}

	public bool GameGet () {
		return game;
	}
	public void GameSet (bool a) {
		game = a;
	}


	public int LanguageNumberGet () {
		return languageNumber;
	}
	public void LanguageNumberSet (int a) {
		languageNumber = a;
	}

	public bool MoveGet () {
		return move;
	}
	public void MoveSet (bool a) {
		move = a;
	}

	public void NumbersSet (int i, int a) {
		numbers [i] = a;
	}
	public int [] NumbersGet () {
		return numbers;
	}

	public int NumberBPSGet () {
		return numberBPS;
	}
	public void NumberBPSSet (int a) {
		numberBPS = a;
	}

	public void Part2DSpriteSet (Sprite [,] a) {
		part2DSprite = a;
	}
	public Sprite [,] Part2DSpriteGet () {
		return part2DSprite;
	}

	public void PartPosSet (int i, int j, int a) {
		partPos [i, j] = a;
	}
	public int [,] PartPosGet () {
		return partPos;
	}

	public int Pow (int a, int b) {
		int c = a;
		for (int i = 0; i < b - 1; i++) {
			a *= c;
		}
		if (b == 0) {
			return 1;
		} else {
			return a;
		}
	}

	public void PrefabInstanceSet (GameObject a) {
		prefabInstance = a;
	}
	public GameObject PrefabInstanceGet () {
		return prefabInstance;
	}

	public void SpriteIn2D (Sprite [] a) {
		for (int i = 0; i < a.Length; i++) {
			part2DSprite [i / 15, i % 15] = a [i];
		}
	}

	public int StringInInt (string a) {
		int b = 0;
		for (int i = a.Length - 1; i >= 0; i--) { 
			switch (a[i]) {
				case '0':
					b += 0 * Pow(10, a.Length - i - 1);
					break; 
				case '1':	
					b += 1 * Pow(10, a.Length - i - 1);
					break; 
				case '2':
					b += 2 * Pow(10, a.Length - i - 1);
					break; 
				case '3':
					b += 3 * Pow(10, a.Length - i - 1);
					break; 
				case '4':
					b += 4 * Pow(10, a.Length - i - 1);
					break; 
				case '5':
					b += 5 * Pow(10, a.Length - i - 1);
					break; 
				case '6':
					b += 6 * Pow(10, a.Length - i - 1);
					break;
				case '7':
					b += 7 * Pow(10, a.Length - i - 1);
					break; 
				case '8':
					b += 8 * Pow(10, a.Length - i - 1);
					break; 
				case '9':
					b += 9 * Pow(10, a.Length - i - 1);
					break;
				default:
				    break; 
			}
		}
		return b;
	}

	public void TextTranslateSet (string [,] a) {
		textTranslate = a;
	}
	public string [,] TextTranslateGet () {
		return textTranslate;
	}

	public void WinSet (bool a) {
		win = a;
	}
	public bool WinGet () {
		return win;
	}
}
