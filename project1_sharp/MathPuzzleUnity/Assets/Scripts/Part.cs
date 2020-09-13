using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Part : MonoBehaviour {

	private Transform localTransform; 
	private int a;
	private int b;
	private int c;
	private float t;
	private bool flag;

	void Start () {
		localTransform = transform;
		a = StringInInt ("" + localTransform.name [4]);
		b = StringInInt ("" + localTransform.name [5]);
	}
	
	void Update () {
		
	}

	void OnMouseDown () {
		if (Contr.instance.MoveGet ()) {
			flag = true;
			if (a != 0) {
				if (Contr.instance.PartPosGet () [a-1, b] == 15) {
					//localTransform.localPosition -= new Vector2 (0, -2.56);
					StartCoroutine (Move (gameObject, localTransform.localPosition, localTransform.localPosition + new Vector3 (0f, 2.56f, 0f)));
					Contr.instance.PartPosSet (a-1, b, Contr.instance.PartPosGet () [a, b]);
					Contr.instance.PartPosSet (a, b, 15);
					a --;
					flag = false;
				}
			}
			if ((a != 3) && (flag)) {
				if (Contr.instance.PartPosGet () [a+1, b] == 15) {
					//localTransform.localPosition += new Vector2 (0, -2.56);
					StartCoroutine (Move (gameObject, localTransform.localPosition, localTransform.localPosition + new Vector3 (0f, -2.56f, 0f)));
					Contr.instance.PartPosSet (a+1, b, Contr.instance.PartPosGet () [a, b]);
					Contr.instance.PartPosSet (a, b, 15);
					a ++;
				}
			}
			flag = true;
			if (b != 0) {
				if (Contr.instance.PartPosGet () [a, b-1] == 15) {
					//localTransform.localPosition -= new Vector2 (2.56, 0);
					StartCoroutine (Move (gameObject, localTransform.localPosition, localTransform.localPosition + new Vector3 (-2.56f, 0f, 0f)));
					Contr.instance.PartPosSet (a, b-1, Contr.instance.PartPosGet () [a, b]);
					Contr.instance.PartPosSet (a, b, 15);
					b --;
					flag = false;
				}
			}
			if ((b != 3) && (flag)) {
				if (Contr.instance.PartPosGet () [a, b+1] == 15) {
					//localTransform.localPosition += new Vector2 (2.56, 0);
					StartCoroutine (Move (gameObject, localTransform.localPosition, localTransform.localPosition + new Vector3 (2.56f, 0f, 0f)));
					Contr.instance.PartPosSet (a, b+1, Contr.instance.PartPosGet () [a, b]);
					Contr.instance.PartPosSet (a, b, 15);
					b ++;
				}
			}
		}
	}

	IEnumerator Move (GameObject go, Vector3 cord, Vector3 cordEnd) {
		t = 0;
		Contr.instance.MoveSet (false);
		while (t < 1) {
			t += Time.deltaTime * 3;
			yield return new WaitForSeconds (0.001f);
			go.transform.localPosition = Vector3.Lerp (cord, cordEnd, t);
		}
		Contr.instance.WinSet (true);
		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j < 4; j ++) {
				if (Contr.instance.PartPosGet () [i, j] != (i * 4 + j)) {
					Contr.instance.WinSet (false);
				}
			}
		}
		Contr.instance.MoveSet (true);
		if (Contr.instance.WinGet ()) {
			Invoke ("WinScreen", 3);
			Contr.instance.MoveSet (false);
		}
	}

	int StringInInt (string a) {
		switch (a) {
			case "0":
				return 0;
				break; 
			case "1":	
				return 1;
				break; 
			case "2":
				return 2;
				break; 
			case "3":
				return 3;
				break; 
			default:
				return 0;
				break; 
			}
	}

	void WinScreen () {
		if (PlayerPrefs.GetInt ("Field") == Contr.instance.FieldGet ()) {
			PlayerPrefs.SetInt ("Field", PlayerPrefs.GetInt ("Field") + 1);
		}
		Contr.instance.winScreen.SetActive (true);
		Contr.instance.playScreen.SetActive (false);
	}
}
