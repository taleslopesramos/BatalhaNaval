package com.teca.batalhanaval;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen {
	final BatalhaNaval game;
	private Texture agua1,agua2,barraStatus;
	private Vector3 touchPos;
	int[] pos;
	OrthographicCamera camera;

	public GameScreen(final BatalhaNaval gam) {
		this.game = gam;
		pos = new int[2];
		// load the images
		agua1 = new Texture(Gdx.files.internal("agua1.png"));
		agua2 = new Texture(Gdx.files.internal("agua2.png"));
		barraStatus = new Texture(Gdx.files.internal("barraStatus.png"));
		// load the drop sound effect

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640+20, 640+20+200);
		
		touchPos = new Vector3();
	}
	
	// x> = 10+ 64*i x<=64+ 64*i
	// y >= 210 + 64*j y<=210 + 64*j

	
	@Override
	public void render(float delta) {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();
		
		game.batch.setProjectionMatrix(camera.combined);
		//640x640
		game.batch.begin();
		for(int i = 0;i < 10;++i){ //desenha o tabuleiro
			for(int j = 0;j < 10;++j){
				if((j+i)%2==0){
					game.batch.draw(agua1, 10+64*i, 210+64*j, 64, 64);
				}
				else{
					game.batch.draw(agua2, 10+64*i, 210+64*j, 64, 64);
				}
			}
		}
		
		game.batch.draw(barraStatus, 10, 10, 640, 190);//desenha a barra de status
		game.batch.end();
		// process user input
		
		if (Gdx.input.justTouched()){
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if((touchPos.x >= 10 + 64*i && touchPos.x <= 74 + 64*i) && 
						(touchPos.y >= 210 + 64*j && touchPos.y <= 210 + 64*(j+1))){
						pos[1]=i;
						pos[0]=9-j;
						Gdx.app.log("render,pos",pos[0]+" "+pos[1]);	
					}
				}
			}
			
		}
			

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown

	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		agua1.dispose();
		agua2.dispose();
	}

}
