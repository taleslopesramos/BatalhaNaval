package com.teca.batalhanaval;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen {

	final BatalhaNaval game;
	private Vector3 touchPos;
	private Texture title;

	private Texture easy;
	private Texture medium;
	private Texture hard;

	OrthographicCamera camera;

	public MainMenuScreen(final BatalhaNaval gam) {
		game = gam;

		touchPos = new Vector3();

		title = new Texture(Gdx.files.internal("title.png"));

		easy = new Texture(Gdx.files.internal("easy.png"));
		medium = new Texture(Gdx.files.internal("medium.png"));
		hard = new Texture(Gdx.files.internal("hard.png"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 1000);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		// game.batch.draw(texture, x, y, width, height);
		game.batch.draw(title, 800 / 2 - 400 / 2, 800, 400, 190);

		game.batch.draw(easy, 800 / 2 - 200 / 2, 500, 200, 100);
		game.batch.draw(medium, 800 / 2 - 200 / 2, 300, 200, 100);
		game.batch.draw(hard, 800 / 2 - 200 / 2, 100, 200, 100);

		game.batch.end();

		if (Gdx.input.justTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (touchPos.x >= 300 && touchPos.x <= 500) {
				if (touchPos.y >= 500 && touchPos.y <= 600) {
					game.setDificuldade("easy");
					game.setScreen(new GameScreen(game));
					dispose();
				}
				if (touchPos.y >= 300 && touchPos.y <= 400) {
					game.setDificuldade("medium");
					game.setScreen(new GameScreen(game));
					dispose();
				}
				if (touchPos.y >= 100 && touchPos.y <= 200) {
					game.setDificuldade("hard");
					game.setScreen(new GameScreen(game));
					dispose();
				}
			}

		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		title.dispose();
		easy.dispose();
		medium.dispose();
		hard.dispose();
	}

}
