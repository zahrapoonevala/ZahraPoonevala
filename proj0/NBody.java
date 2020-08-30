public class NBody {

		public static double readRadius(String file){
			In temp_in = new In(file);
			int number = temp_in.readInt();
			double radius = temp_in.readDouble();

			return radius; 


	}

		public static Body [] readBodies(String filename){
			In temp_in = new In(filename);
			int number = temp_in.readInt();
			double radius = temp_in.readDouble();
			Body [] bodies_list = new Body[number];

			for (int i = 0; i < number; i++){
				double xxPos = temp_in.readDouble();
				double yyPos = temp_in.readDouble();
				double xV = temp_in.readDouble();
				double yV = temp_in.readDouble();
				double m = temp_in.readDouble();
				String img = temp_in.readString();

				bodies_list[i] = new Body (xxPos, yyPos, xV, yV, m, img);

			}
		
		return bodies_list;


		}

		public static void main(String[] args) {
			double T = Double.parseDouble(args[0]);
			double dt = Double.parseDouble(args[1]);
			String filename = args[2];

			double radius = readRadius(filename);
			Body [] bodies_list = readBodies(filename);

			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (Body i: bodies_list){
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
				
				for (Body i: bodies_list){
					i.draw();
				}

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

}