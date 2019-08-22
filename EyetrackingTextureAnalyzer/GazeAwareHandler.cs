using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Tobii.Gaming;

public class GazeAwareHandler : MonoBehaviour
{
    private const int DELTA_CHECK = 25;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    public Texture2D DeCompress(this Texture2D source)
    {
        RenderTexture renderTex = RenderTexture.GetTemporary(
                    source.width,
                    source.height,
                    0,
                    RenderTextureFormat.Default,
                    RenderTextureReadWrite.Linear);

        Graphics.Blit(source, renderTex);
        RenderTexture previous = RenderTexture.active;
        RenderTexture.active = renderTex;
        Texture2D readableText = new Texture2D(source.width, source.height);
        readableText.ReadPixels(new Rect(0, 0, renderTex.width, renderTex.height), 0, 0);
        readableText.Apply();
        RenderTexture.active = previous;
        RenderTexture.ReleaseTemporary(renderTex);
        return readableText;
    }


    // Update is called once per frame
    void Update()
    {
        Ray r;
        RaycastHit hit;

        GazePoint gp = TobiiAPI.GetGazePoint();

        if(gp.IsValid && (Time.frameCount % DELTA_CHECK) == 0)
        {
            
            r = Camera.main.ScreenPointToRay(gp.Screen);

            if(Physics.Raycast(r, out hit,  Mathf.Infinity))
            {
                Material m = hit.collider.GetComponent<Renderer>().material;


                if (!System.IO.File.Exists("test.jpg"))
                {
                    Texture2D tt2 = m.mainTexture as Texture2D;

                    Texture2D readable = DeCompress(tt2);

                    byte[] file = readable.EncodeToPNG();

                    System.IO.File.WriteAllBytes("test.png", file);

                }
            }
        }

    }
}
