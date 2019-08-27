using System;
using System.Collections.Generic;
using Tobii.Gaming;

using Newtonsoft.Json;
using UnityEngine;

[Serializable]
public class ObjectGazeContainer
{
    
    public List<GazePoint> gazePoints;
    public List<Vector2> UVCoord;
    public string id;

	public ObjectGazeContainer(string id)
	{
        gazePoints = new List<GazePoint>();
        UVCoord = new List<Vector2>();
        this.id = id;
	}

    public void addUVCoord(Vector2 add)
    {
        UVCoord.Add(add);
    }

    public void addGazePoint(GazePoint gp)
    {
        gazePoints.Add(gp);
    }

    public GazePoint getGazePoint(int position)
    {
        return this.gazePoints[position];
    }

    public void removeGazePoint(int position)
    {
        this.gazePoints.RemoveAt(position);
    }

    public string toJSON()
    {
        return JsonConvert.SerializeObject(this);   
    }

    public string getID()
    {
        return this.id;
    }
    
    public List<GazePoint> GetGazePoints()
    {
        return this.gazePoints;
    }
}