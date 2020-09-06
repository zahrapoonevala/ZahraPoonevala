  
import java.awt.event.KeyEvent;

public class NBodyExtreme {

		public static double spaceCraftXForce;
		public static double spaceCraftYForce;


		public static double readRadius(String file){
			In temp_in = new In(file);
			int number = temp_in.readInt();
			double radius = temp_in.readDouble();

			return radius; 


	}

		public static BodyExtreme spaceCraft = new BodyExtreme(2.2e+11, 0, 0, 2.4e+04, 6e+23, "Spaceship.png");

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
			spaceCraft.draw();

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

				spaceCraftXForce = spaceCraft.calcNetForceExertedByX(bodies_list);
				spaceCraftYForce = spaceCraft.calcNetForceExertedByY(bodies_list);
				spaceCraft.update(dt, spaceCraftXForce, spaceCraftYForce);
				control(dt, bodies_list);

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

		//@souce"https://stackoverflow.com/questions/18037576/how-do-i-check-if-the-user-is-pressing-a-key"

		public static void control (double dt, BodyExtreme [] bodies_list){
			if (StdDraw.isKeyPressed('W')){
				spaceCraft.yyPos += 10e8;
			}

			if (StdDraw.isKeyPressed('A')){
				spaceCraft.yyPos -= 10e8;
			}

			if (StdDraw.isKeyPressed('D')){
				spaceCraft.yyPos += 10e8;
			}

			if (StdDraw.isKeyPressed('S')){
				spaceCraft.yyPos -= 10e8;
			}
		}

}