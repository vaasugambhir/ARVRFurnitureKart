using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FlickerComponent : MonoBehaviour
{
    public bool isFlickering = false;
    public float timeDelay;

    // Update is called once per frame
    void Update()
    {
        if(isFlickering == false){
            StartCoroutine(FlickeringLight());
        }
    }
    IEnumerator FlickeringLight(){
        isFlickering =true;
        this.gameObject.GetComponent<Light>().enabled = true;
        timeDelay = 0.7f;
        yield return new WaitForSeconds(timeDelay);
        this.gameObject.GetComponent<Light>().enabled = false;
        timeDelay = 0.4f;
        yield return new WaitForSeconds(timeDelay);
        isFlickering = false;
    }
}
