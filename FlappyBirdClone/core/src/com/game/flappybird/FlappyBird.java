package com.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture[] passaros;
	private Texture fundo;
	private Texture canoBaixo;
	private Texture canoTopo;
	private Random numeroRandomico;
	private BitmapFont fonte;
	private BitmapFont mensagem;
	private Circle passaroCirculo;
	private Rectangle retanguloCanoTopo;
	private Rectangle retanguloCanoBaixo;
	private ShapeRenderer shape;
	private Texture gameOver;

	//Atributos de configuração
	private float laguraDispositivo;
	private float alturaDispositivo;
	private int estadoJogo=0;// 0->jogo não iniciado  1->jogo iniciado  2->Game Over
    private int pontuacao=0;

	private float variacao=0;
	private float velocidadeQueda=0;
	private float posicaoInicialVertical;
	private float posicaoMovimentoCanoHorizontal;
	private float espacoEntreCanos;
	private float deltaTime;
    private float alturaEntreCanosRadomica;
    private boolean marcouPonto=false;

    //Câmera
	private OrthographicCamera camera;
	private Viewport viewport;
	private final float VIRTUAL_WIDTH = 768;
	private final float VIRTUAL_HEIGHT = 1024;

	@Override
	public void create () {
		batch = new SpriteBatch();
		numeroRandomico = new Random();
		passaroCirculo = new Circle();
		retanguloCanoTopo = new Rectangle();
		retanguloCanoBaixo = new Rectangle();
        shape = new ShapeRenderer();


		fonte = new BitmapFont();
		fonte.setColor(Color.WHITE);
		fonte.getData().setScale(6);

		mensagem = new BitmapFont();
		mensagem.setColor(Color.WHITE);
		mensagem.getData().setScale(3);

		passaros = new Texture[3];
		passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");
        passaros[2] = new Texture("passaro3.png");

		fundo = new Texture("fundo.png");
		canoBaixo = new Texture("cano_baixo.png");
		canoTopo = new Texture("cano_topo.png");
		gameOver = new Texture("game_over.png");

		/*  Configuração da camera */
		camera = new OrthographicCamera();
		camera.position.set(VIRTUAL_WIDTH/2, VIRTUAL_HEIGHT/2, 0);
		viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);


		laguraDispositivo= VIRTUAL_WIDTH;
		alturaDispositivo= VIRTUAL_HEIGHT;
		posicaoInicialVertical=alturaDispositivo/2;
		posicaoMovimentoCanoHorizontal = laguraDispositivo-100;
		espacoEntreCanos = 200;
	}

	@Override
	public void render () {

		camera.update();

		//limpar frames anteriores
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        deltaTime = Gdx.graphics.getDeltaTime();
        variacao += deltaTime * 5;

        if (variacao > 2) {
            variacao = 0;
        }

	    if( estadoJogo == 0 ){ //Não iniciado
	        if( Gdx.input.justTouched() ){
	            estadoJogo = 1;
            }
        } else {

			velocidadeQueda += 0.5;
			if (posicaoInicialVertical > 0 || velocidadeQueda < 0) {
				posicaoInicialVertical -= velocidadeQueda;
			}

	    	if(estadoJogo==1){
				Gdx.app.log("Variacao", "Variacao" + deltaTime);
				posicaoMovimentoCanoHorizontal -= deltaTime * 200;

				if (Gdx.input.justTouched()) {
					Gdx.app.log("Toque", "Toque na tela");

					//posicaoInicialVertical =posicaoInicialVertical - velocidadeQueda
					//5
					velocidadeQueda = -10;
				}

				//Verifica se o cano saiu da tela
				if (posicaoMovimentoCanoHorizontal < -canoTopo.getWidth()) {
					posicaoMovimentoCanoHorizontal = laguraDispositivo;
					alturaEntreCanosRadomica = numeroRandomico.nextInt(400)-200;
					marcouPonto = false;
				}

				//Verifica pontuacao
				if(posicaoMovimentoCanoHorizontal<120) {
					if(!marcouPonto){
						pontuacao++;
						marcouPonto = true;
					}
				}

			} else { // tela game over

	    		if(Gdx.input.justTouched()) {
	    			estadoJogo = 0;
	    			pontuacao = 0;
	    			velocidadeQueda = 0;
	    			posicaoInicialVertical = alturaDispositivo/2;
	    			posicaoMovimentoCanoHorizontal = laguraDispositivo;
				}
			}


        }

	    //Configurar dados de projeção da câmera
		batch.setProjectionMatrix( camera.combined );

	    //draw scenario
		batch.begin();
        	drawBackground();
        	drawPipes();
        	drawBird();
			drawScore();
			if(estadoJogo==2) {
				batch.draw(gameOver, laguraDispositivo/2 - gameOver.getWidth()/2 , alturaDispositivo/2);
				mensagem.draw(batch, "Toque para Reiniciar!" ,laguraDispositivo/2 - gameOver.getWidth()/2 -10, alturaDispositivo/2 -gameOver.getHeight()/2);
			}
		batch.end();

		makeForms();
		//drawForms();

		//Teste de colisão
		if(Intersector.overlaps( passaroCirculo, retanguloCanoBaixo) || Intersector.overlaps(passaroCirculo, retanguloCanoTopo) || posicaoInicialVertical <=0 || posicaoInicialVertical>= alturaDispositivo) {
			Gdx.app.log("Colisao", "Houve colisão");
			estadoJogo=2;
		}
	}

	public void drawBackground(){
        batch.draw(fundo, 0,0, laguraDispositivo, alturaDispositivo);
    }

    public void drawPipes() {
        batch.draw(canoTopo, posicaoMovimentoCanoHorizontal, alturaDispositivo/2 + espacoEntreCanos/2 + alturaEntreCanosRadomica);
        batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal, alturaDispositivo/2 - canoBaixo.getHeight() - espacoEntreCanos/2 + alturaEntreCanosRadomica);
    }

    public void drawBird() {
        batch.draw(passaros[(int)variacao], 120, posicaoInicialVertical);
    }

    public void drawScore() {
		fonte.draw(batch, String.valueOf(pontuacao), laguraDispositivo/2, alturaDispositivo-50);
	}

	public void makeForms() {
		passaroCirculo.set(120 + passaros[0].getWidth() / 2 , posicaoInicialVertical + passaros[0].getHeight() / 2, passaros[0].getWidth()/2);

		retanguloCanoBaixo = new Rectangle(
				posicaoMovimentoCanoHorizontal,
				alturaDispositivo/2 - canoBaixo.getHeight() - espacoEntreCanos/2 + alturaEntreCanosRadomica,
				canoBaixo.getWidth(),
				canoBaixo.getHeight()
		);

		retanguloCanoTopo = new Rectangle(
				posicaoMovimentoCanoHorizontal,
				alturaDispositivo/2 + espacoEntreCanos/2 + alturaEntreCanosRadomica,
				canoTopo.getWidth(),
				canoTopo.getHeight());
	}

    public void drawForms() {
		shape.begin( ShapeRenderer.ShapeType.Filled );

		shape.circle(passaroCirculo.x, passaroCirculo.y, passaroCirculo.radius);
		shape.rect(retanguloCanoBaixo.x, retanguloCanoBaixo.y, retanguloCanoBaixo.width, retanguloCanoBaixo.height);
		shape.rect(retanguloCanoTopo.x, retanguloCanoTopo.y, retanguloCanoTopo.width, retanguloCanoTopo.height);
		shape.setColor(Color.RED);

		shape.end();
	}

	/*
	 * O método dispose () do ApplicationListener é o método
	 * do ciclo de vida e chamado quando o aplicativo é destruído.
	 * Quaisquer recursos descartáveis que você criou no método create ()
	 * devem ser destruídos nesse método.
	  * */
	@Override
	public void dispose () {
		//batch.dispose();
		//img.dispose();
		batch.dispose();
		passaros[0].dispose();
        passaros[1].dispose();
        passaros[2].dispose();
		fundo.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}