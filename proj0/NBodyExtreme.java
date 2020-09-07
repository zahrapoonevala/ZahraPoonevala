  
import java.awt.event.KeyEvent;

public class NBodyExtreme {

		public static double spaceCraftXForce = 0.0;
		public static double spaceCraftYForce = 0.0;
		public static double displacementChange = 10e8;


		public static double readRadius(String file){
			In temp_in = new In(file);
			int number = temp_in.readInt();
			double radius = temp_in.readDouble();

			return radius; 


	}
		//Created my new spacecraft using trial and error for the numbers based on the other planets numbers

		public static BodyExtreme spaceCraft = new BodyExtreme(2.1e+11, 0, 0, 1.8e+04, 5e+20, "Spaceship.png");

		public static BodyExtreme [] readBodies(String filename){
			In temp_in = new In(filename);
			int number = temp_in.readInt();
			double radius = temp_in.readDouble();
			BodyExtreme [] bodies_list = new BodyExtreme[number];

			for (int i = 0; i < number; i++){
				double xxPos = temp_in.readDouble();
				double yyPos = temp_in.readDouble();
				double xV = temp_in.readDouble();
				double yV = temp_in.readDouble();
				double m = temp_in.readDouble();
				String img = temp_in.readString();

				bodies_list[i] = new BodyExtreme (xxPos, yyPos, xV, yV, m, img);

			}
		
		return bodies_list;


		}

		public static void main(String[] args) {
			double T = Double.parseDouble(args[0]);
			double dt = Double.parseDouble(args[1]);
			String filename = args[2];

			double radius = readRadius(filename);
			BodyExtreme [] bodies_list = readBodies(filename);

			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			

			for (BodyExtreme i: bodies_list){
				i.draw();
			}

			StdDraw.enableDoubleBuffering();

			double time = 0;

			while(time < T){
				double [] xForces = new double [bodies_list.length];
				double [] yForces = new double [bodies_list.length];

				for (int i = 0; i < bodies_list.length; i++){
					xForces[i] = bodies_list[i].calcNetForceExertedByX(bodies_list);
					yForces[i] = bodies_list[i].calcNetForceExertedByY(bodies_list);
				}

				for (int i = 0; i < bodies_list.length; i++){
					bodies_list[i].update(dt, xForces[i], yForces[i]);
				}

				StdDraw.picture(0, 0, "images/starfield.jpg");
				
				for (BodyExtreme i: bodies_list){
					i.draw();
				}

				spaceCraft.draw();
				spaceCraftXForce = spaceCraft.calcNetForceExertedByX(bodies_list); //Calculating the x force felt by the spacecraft due to the other objects
				spaceCraftYForce = spaceCraft.calcNetForceExertedByY(bodies_list); //Calculating the y force felt by the spacecraft due to the other objects 
				spaceCraft.update(dt, spaceCraftXForce, spaceCraftYForce); //Updating with this data 
				control();// Calling control to control the spacecraft 
				

				StdDraw.show();
				StdDraw.pause(10);
				time += dt;

			}

			StdOut.printf("%d\n", bodies_list.length);
			StdOut.printf("%.2e\n", radius);

			for (int i = 0; i < bodies_list.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies_list[i].xxPos, bodies_list[i].yyPos, bodies_list[i].xxVel,
                  bodies_list[i].yyVel, bodies_list[i].mass, bodies_list[i].imgFileName);   
			}	


		} 

		//@source : https://stackoverflow.com/questions/18037576/how-do-i-check-if-the-user-is-pressing-a-key
		//@source : https://www.psmsl.org/train_and_info/training/gloss/gb/gb3/abgrav.html (for the displacement change value)
		public static void control (){
			if (StdDraw.isKeyPressed('I')){
				spaceCraft.yyPos += displacementChange;
			}

			if (StdDraw.isKeyPressed('J')){
				spaceCraft.xxPos -= displacementChange;
			}

			if (StdDraw.isKeyPressed('L')){
				spaceCraft.xxPos += displacementChange;
			}

			if (StdDraw.isKeyPressed('K')){
				spaceCraft.yyPos -= displacementChange;
			}

			if (StdDraw.isKeyPressed('C')){
				spaceCraft.xxPos = 5.6e+10;
				spaceCraft.yyPos = 0;
				spaceCraft.xxVel = 0;
				spaceCraft.yyVel = 1.8e+04; 
		}
}
}