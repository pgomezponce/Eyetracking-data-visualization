using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Tobii.Gaming;
using Newtonsoft.Json;

namespace Assets.Scripting
{
    public class GazeAwareHandler : MonoBehaviour
    {
        private const int DELTA_CHECK = 25;
        private const string PATH = "output/";

        // Start is called before the first frame update
        void Start()
        {

        }

        private Texture2D DeCompress(Texture2D source)
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

            if (gp.IsValid && Time.frameCount % DELTA_CHECK == 0)
            {

                r = Camera.main.ScreenPointToRay(gp.Screen);

                if (Physics.Raycast(r, out hit, Mathf.Infinity))
                {
                    Material m = hit.collider.GetComponent<Renderer>().material;
                    ObjectGazeContainer ogc;
                    string hitObjectName = hit.collider.name;
                   
                    if (!System.IO.File.Exists( PATH + hitObjectName  + "/" + hitObjectName + ".png"))
                    {
                        //Generate info handler
                        ogc = new ObjectGazeContainer(hitObjectName);

                        //add empty data
                        ogc.addGazePoint(new GazePoint(Vector2.zero, -10, -10));
                        
                        //Generate image file
                        Texture2D tt2 = m.mainTexture as Texture2D;

                        Texture2D readable = DeCompress(tt2);

                        byte[] file = readable.EncodeToPNG();

                        System.IO.Directory.CreateDirectory(PATH + hitObjectName);

                        System.IO.File.WriteAllBytes( PATH + hitObjectName + "/" + hitObjectName + ".png", file);

                        System.IO.File.WriteAllText( PATH + hitObjectName + "/" + hitObjectName + ".json", JsonConvert.SerializeObject( ogc ));
                    }

                    string json = System.IO.File.ReadAllText( PATH + hitObjectName + ".json");
                    ogc = JsonConvert.DeserializeObject<ObjectGazeContainer>(json);

                    ogc.addGazePoint(gp);
                    ogc.addUVCoord(hit.textureCoord);

                    System.IO.File.WriteAllText( PATH + hitObjectName + ".json", JsonConvert.SerializeObject(ogc));
                }
            }

        }
    }
}