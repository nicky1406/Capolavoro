package modulo.FX;

import javax.imageio.ImageIO;
import javax.swing.*;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class View extends JFrame {
	private JPanel Menu;
	private Dimension screenSize;

	private JButton Lancia, Lancia2, Gol, noGol;

	private JFXPanel fxPanel;

	private int s = 100, d, d2, d3, p, g, gol;

	private JLabel saldoLabel, winLabel, Card, Card2, Card3;

	private JTextField puntataTextField;

	private Random ran = new Random();

	public View() {
		setTitle("Capolavoro");
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setupMenu();
		setVisible(true);
	}

	private void setupMenu() {
		Menu = new ImagePanel(getScaledImage("/IMG/background.png"));
		Menu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);

		// Titolo
		JLabel t = new JLabel("Capolavoro");
		t.setFont(new Font("Arial", Font.BOLD, 60));
		t.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		Menu.add(t, gbc);

		// Bottone 1
		JButton button1 = new JButton("Slot Machine");
		button1.setFont(new Font("Arial", Font.BOLD, 24));
		button1.setForeground(Color.black);
		button1.setBackground(new Color(255, 204, 0)); // Giallo
		button1.setBorderPainted(false);
		button1.setFocusPainted(false);
		button1.addActionListener(e -> Slot());
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		Menu.add(button1, gbc);

		// Bottone 2
		JButton button2 = new JButton("Virtual");
		button2.setFont(new Font("Arial", Font.BOLD, 24));
		button2.setForeground(Color.black);
		button2.setBackground(new Color(255, 204, 0)); // Giallo
		button2.setBorderPainted(false);
		button2.setFocusPainted(false);
		button2.addActionListener(e -> virtual());
		gbc.gridy = 3;
		Menu.add(button2, gbc);
		this.add(Menu);
	}

	private Image getScaledImage(String imagePath) {
		try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
			BufferedImage image = ImageIO.read(inputStream);
			return image.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// JPanel personalizzato con sfondo immagine
	class ImagePanel extends JPanel {
		private Image image;

		public ImagePanel(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}

	public void Slot() {
		// Crea un nuovo pannello per i nuovi componenti della Slot Machine
		JPanel nuovoPanello = new ImagePanel(getScaledImage("/IMG/background.png"));
		nuovoPanello.setSize(getMaximumSize());
		nuovoPanello.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;

		// Titolo
		JLabel titoloLabel = new JLabel("Slot Machine");
		titoloLabel.setFont(new Font("Arial", Font.BOLD, 50));
		titoloLabel.setForeground(Color.red);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		nuovoPanello.add(titoloLabel, gbc);
		gbc.gridwidth = 1;

		// Logo
		ImageIcon icon = new ImageIcon(getClass().getResource("/IMG/Logo.png"));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		JLabel imageLabel = new JLabel(scaledIcon);
		imageLabel.setPreferredSize(new Dimension(200, 200));
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setVerticalAlignment(JLabel.CENTER);
		gbc.gridx = 1;
		gbc.gridy = 1;
		nuovoPanello.add(imageLabel, gbc);

		// Card1
		Card = new JLabel();
		Card.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 2;
		nuovoPanello.add(Card, gbc);

		// Card2
		Card2 = new JLabel();
		Card2.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 1;
		nuovoPanello.add(Card2, gbc);

		// Card3
		Card3 = new JLabel();
		Card3.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 2;
		nuovoPanello.add(Card3, gbc);

		// Pannello per saldo e puntata
		JPanel controlPanel = new JPanel(new GridBagLayout());
		controlPanel.setBackground(new Color(0, 0, 0, 150)); // sfondo trasparente nero
		controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		GridBagConstraints gbcControl = new GridBagConstraints();
		gbcControl.insets = new Insets(5, 5, 5, 5);
		gbcControl.fill = GridBagConstraints.HORIZONTAL;

		// Saldo
		saldoLabel = new JLabel("Saldo: " + s);
		saldoLabel.setFont(new Font("Arial", Font.BOLD, 20));
		saldoLabel.setForeground(Color.white);
		gbcControl.gridx = 0;
		gbcControl.gridy = 0;
		gbcControl.gridwidth = 2;
		controlPanel.add(saldoLabel, gbcControl);

		// Puntata
		JLabel puntataTextLabel = new JLabel("Puntata: ");
		puntataTextLabel.setFont(new Font("Arial", Font.BOLD, 20));
		puntataTextLabel.setForeground(Color.white);
		puntataTextField = new JTextField(String.valueOf(p), 10);
		puntataTextField.setFont(new Font("Arial", Font.PLAIN, 20));
		gbcControl.gridx = 0;
		gbcControl.gridy = 1;
		gbcControl.gridwidth = 1;
		controlPanel.add(puntataTextLabel, gbcControl);
		gbcControl.gridx = 1;
		controlPanel.add(puntataTextField, gbcControl);

		// Vincita
		winLabel = new JLabel("Vincita: 0");
		winLabel.setFont(new Font("Arial", Font.BOLD, 20));
		winLabel.setForeground(Color.white);
		gbcControl.gridx = 0;
		gbcControl.gridy = 2;
		gbcControl.gridwidth = 2;
		controlPanel.add(winLabel, gbcControl);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		nuovoPanello.add(controlPanel, gbc);
		gbc.gridwidth = 1;

		// Indietro
		JButton backButton = new JButton("Torna al menu");
		backButton.setFont(new Font("Arial", Font.BOLD, 18));
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.CENTER;
		nuovoPanello.add(backButton, gbc);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().remove(nuovoPanello);
				add(Menu);
				revalidate();
				repaint();
			}
		});

		// Lancia
		Lancia = new JButton("Lancia il dado");
		Lancia.setFont(new Font("Arial", Font.BOLD, 18));
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.CENTER;
		nuovoPanello.add(Lancia, gbc);

		Lancia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lanciaDado();
			}
		});

		// Rimuovi tutti i componenti dal pannello Menu
		getContentPane().remove(Menu);
		// Aggiungi il nuovo pannello con i nuovi componenti
		add(nuovoPanello);
		// Rendi effettive le modifiche di layout
		revalidate();
		// Ridisegna il frame
		repaint();
	}

	private void lanciaDado() {
		ran = new Random();

		Lancia.setEnabled(false);
		long startTime = System.currentTimeMillis();
		Thread threadD = new Thread(new Runnable() {

			@Override
			public void run() {
				long endTime = System.currentTimeMillis();
				while ((endTime - startTime) / 1000 < 2) { // Fa aspettare 2 secondi
					d = ran.nextInt(1, 7); // 7 è escluso
					d2 = ran.nextInt(1, 7);
					d3 = ran.nextInt(1, 7);
					updateImage(d, d2, d3);
					endTime = System.currentTimeMillis();
				}
				processResult();
				Lancia.setEnabled(true);
			}
		});
		threadD.start();
	}

	private void updateImage(int d, int d2, int d3) {
		Lancia.setEnabled(false);
		int imageSize = 200; // Modifica le dimensioni come preferisci

		ImageIcon i = new ImageIcon(getClass().getResource("/IMG/" + d + ".png"));
		Image image1 = i.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon1 = new ImageIcon(image1);
		Card.setIcon(scaledIcon1);

		ImageIcon i2 = new ImageIcon(getClass().getResource("/IMG/" + d2 + ".png"));
		Image image2 = i2.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon2 = new ImageIcon(image2);
		Card2.setIcon(scaledIcon2);

		ImageIcon i3 = new ImageIcon(getClass().getResource("/IMG/" + d3 + ".png"));
		Image image3 = i3.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon3 = new ImageIcon(image3);
		Card3.setIcon(scaledIcon3);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Lancia.setEnabled(true);
	}

	private void processResult() {
		int puntata = Integer.parseInt(puntataTextField.getText());
		if (d == d2 && d2 == d3) {
			s += puntata * 10;
			winLabel.setText("Vincita: " + puntata * 10);
			JOptionPane.showMessageDialog(this, "Hai vinto!");
		} else {
			s -= puntata;
			winLabel.setText("Vincita: 0");
			JOptionPane.showMessageDialog(this, "Hai perso!");
		}
		saldoLabel.setText("Saldo: " + s);
	}

	private void virtual() {
		// Pannello principale
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);

		// Pannello centrale
		fxPanel = new JFXPanel();

		// Pannello laterale per il controllo
		JPanel controlPanel = new JPanel(new GridBagLayout());
		controlPanel.setBackground(Color.yellow); // sfondo trasparente nero
		controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		GridBagConstraints gbcControl = new GridBagConstraints();
		gbcControl.insets = new Insets(5, 5, 5, 5);
		gbcControl.fill = GridBagConstraints.HORIZONTAL;

		// Saldo
		JLabel saldoLabel = new JLabel("Saldo: " + s);
		saldoLabel.setFont(new Font("Arial", Font.BOLD, 20));
		saldoLabel.setForeground(Color.black);
		gbcControl.gridx = 0;
		gbcControl.gridy = 0;
		gbcControl.gridwidth = 2;
		controlPanel.add(saldoLabel, gbcControl);

		// Puntata
		JLabel puntataTextLabel = new JLabel("Puntata: ");
		puntataTextLabel.setFont(new Font("Arial", Font.BOLD, 20));
		puntataTextLabel.setForeground(Color.black);
		JTextField puntataTextField = new JTextField(String.valueOf(p), 10);
		puntataTextField.setFont(new Font("Arial", Font.PLAIN, 20));
		gbcControl.gridx = 0;
		gbcControl.gridy = 1;
		gbcControl.gridwidth = 1;
		controlPanel.add(puntataTextLabel, gbcControl);
		gbcControl.gridx = 1;
		controlPanel.add(puntataTextField, gbcControl);

		// Vincita
		JLabel winLabel = new JLabel("Vincita: " + p * 2);
		winLabel.setFont(new Font("Arial", Font.BOLD, 20));
		winLabel.setForeground(Color.black);
		gbcControl.gridx = 0;
		gbcControl.gridy = 2;
		gbcControl.gridwidth = 2;
		controlPanel.add(winLabel, gbcControl);

		// Pannello superiore
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.yellow); // sfondo trasparente nero
		JLabel titoloLabel = new JLabel("Slot Machine");
		titoloLabel.setFont(new Font("Arial", Font.BOLD, 50));
		titoloLabel.setForeground(Color.red);
		northPanel.add(titoloLabel);

		// Pannello inferiore per i bottoni e la scommessa
		JPanel buttonAndBetPanel = new JPanel(new BorderLayout());

		// Pannello per i bottoni
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(Color.yellow); // sfondo trasparente nero
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		GridBagConstraints gbcButton = new GridBagConstraints();
		gbcButton.insets = new Insets(5, 5, 5, 5);
		gbcButton.fill = GridBagConstraints.HORIZONTAL;

		// Bottone per "Gol"
		JButton golButton = new JButton("Gol");
		golButton.setFont(new Font("Arial", Font.BOLD, 18));
		golButton.setBackground(Color.GREEN);
		golButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g = ran.nextInt(1, 12);
				gol = 1;
				riproduzionevideo(g, true, Integer.parseInt(puntataTextField.getText()), saldoLabel, winLabel);
			}
		});

		gbcButton.gridx = 0;
		gbcButton.gridy = 0;
		buttonPanel.add(golButton, gbcButton);

		// Bottone per "No Gol"
		JButton noGolButton = new JButton("No Gol");
		noGolButton.setFont(new Font("Arial", Font.BOLD, 18));
		noGolButton.setBackground(Color.RED);
		noGolButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g = ran.nextInt(1, 12);
				gol = 0;
				riproduzionevideo(g, false, Integer.parseInt(puntataTextField.getText()), saldoLabel, winLabel);
			}
		});

		gbcButton.gridx = 1;
		buttonPanel.add(noGolButton, gbcButton);

		// Bottone per "Torna al menu"
		JButton backButton = new JButton("Torna al menu");
		backButton.setFont(new Font("Arial", Font.BOLD, 18));
		backButton.setBackground(Color.white);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().remove(mainPanel);
				getContentPane().remove(fxPanel);
				add(Menu);
				revalidate();
				repaint();
			}
		});

		gbcButton.gridx = 2;
		buttonPanel.add(backButton, gbcButton);

		// Aggiungi pannelli al pannello principale
		JPanel n=new JPanel();
		n.setPreferredSize(new Dimension(250,250));
		n.setBackground(Color.yellow);
		mainPanel.add(n,BorderLayout.WEST);
		mainPanel.add(controlPanel, BorderLayout.EAST);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(fxPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Rimuovi il JPanel Menu dal frame principale
		getContentPane().remove(Menu);

		// Aggiungi il nuovo JPanel al frame principale
		add(mainPanel);

		// Rendi effettive le modifiche di layout
		revalidate();
		repaint();
	}

	private void riproduzionevideo(int g, boolean isGol, int puntata, JLabel saldoLabel, JLabel winLabel) {
		// Carica il video
		JOptionPane.showMessageDialog(this, "Clicca per far partire il video");
		File videoFile = new File("src\\Video\\" + g + ".mp4");
		Media media = new Media(videoFile.toURI().toString());

		// Crea il MediaPlayer per la riproduzione del video
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);

		// Crea la vista media per visualizzare il video
		MediaView mediaView = new MediaView(mediaPlayer);

		// Imposta la dimensione preferita del pannello JavaFX
		mediaView.setFitWidth(700); // Imposta la larghezza desiderata
		mediaView.setFitHeight(700); // Imposta l'altezza desiderata

		// Crea la scena e aggiungi la vista media ad essa
		javafx.scene.Scene scene = new javafx.scene.Scene(new javafx.scene.layout.BorderPane(mediaView));

		// Imposta la scena nel JFXPanel
		fxPanel.setScene(scene);

		// Imposta la visibilità del JFrame
		setVisible(true);

		// Imposta l'azione da eseguire alla fine del video
		mediaPlayer.setOnEndOfMedia(() -> {
			// Controllo del risultato e aggiornamento dei label
			if (isGol && g <= 6 || !isGol && g > 6) {
				processBet(true, puntata, saldoLabel, winLabel);
			} else {
				processBet(false, puntata, saldoLabel, winLabel);
			}
		});
	}

	private void processBet(boolean isGol, int puntata, JLabel saldoLabel, JLabel winLabel) {
		if (isGol && g <= 6 || !isGol && g > 6) {
			s += puntata * 2;
			winLabel.setText("Vincita: " + puntata * 2);
			JOptionPane.showMessageDialog(this, "HAI VINTO");
		} else {
			s -= puntata;
			winLabel.setText("Vincita: 0");
			JOptionPane.showMessageDialog(this, "HAI PERSO");
		}
		saldoLabel.setText("Saldo: " + s);
	}
}
