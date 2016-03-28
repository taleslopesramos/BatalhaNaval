package com.teca.batalhanaval;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BatalhaNaval extends Game {
	public SpriteBatch batch;
    private String dificuldade;
   
    public void setDificuldade(String str){
    	dificuldade = str; 
    }
    
    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
      
        this.setScreen(new MainMenuScreen(this));
        
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();

    }
}
