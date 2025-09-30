import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class Mole {
	ImageIcon marioIcon=new ImageIcon("marioImage.png");
	ImageIcon plantIcon =new ImageIcon("plant.jpg");
	JFrame frame=new JFrame("wrack a mole");
	JLabel textLabel=new JLabel();
	JPanel textPanel=new JPanel();
	JPanel boardPanel  =new JPanel();
	JButton[] boardButton=new JButton[9]; 
	
	JButton currentMarioTile=new JButton();
	JButton currentPlantTile=new JButton();
	
	Random random =new Random();
	Timer marioTimer;
	Timer plantTimer;
	int score=0;
	
	Mole(){

		frame.setSize(600,650);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		textLabel.setText("Score:");
		textLabel.setFont(new Font("Arial",Font.PLAIN,50));
		textPanel.setLayout(new BorderLayout());
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setOpaque(true);
		textPanel.add(textLabel);
		frame.add(textPanel,BorderLayout.NORTH);
		
		Image marioImage = marioIcon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);
		 marioIcon.setImage(marioImage);
		Image plantImage=plantIcon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);
		plantIcon.setImage(plantImage);
		
		
		
		
		boardPanel.setLayout(new GridLayout(3,3));
		for(int i=0;i<9;i++) {
			JButton tiles=new JButton();
			boardButton[i]=tiles;
			boardPanel.add(tiles);
			tiles.setFocusable(false);
			//tiles.setIcon(marioIcon);
			tiles.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton tile=(JButton)e.getSource();
					if(tile==currentMarioTile) {
						score+=10;
						textLabel.setText("Score:"+Integer.toString(score));
					}
					if(tile==currentPlantTile) {
						textLabel.setText("Game over"+Integer.toString(score));
						marioTimer.stop();
						plantTimer.stop();
						for(int i=0;i<9;i++) {
							boardButton[i].setEnabled(false);
						}
					}
				}
				
			});
			
			
		}
		
		
		frame.add(boardPanel);
		
		
		marioTimer=new Timer(1000, new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if(currentMarioTile!=null) {
					currentMarioTile.setIcon(null);
					currentMarioTile=null;
				}
				JButton tile;
				 do {
			            int num = random.nextInt(9);
			            tile = boardButton[num];
			        } while (tile == currentPlantTile);  // retry until different from plant

				
				currentMarioTile=tile;
				currentMarioTile.setIcon(marioIcon);
			
		}
			
			});
		
		plantTimer=new Timer(1000,new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(currentPlantTile!=null) {
					currentPlantTile.setIcon(null);
					currentPlantTile=null;
				}
				JButton tile;
				 do {
			            int num = random.nextInt(9);
			            tile = boardButton[num];
			        } while (tile == currentMarioTile);  // retry until different from plant

				currentPlantTile=tile;
				currentPlantTile.setIcon(plantIcon);
			}


		});
		marioTimer.start();
		plantTimer.start();
		frame.setVisible(true);
		
	}

}
