import java.util.ArrayList;

public class BodyExtreme {

public double xxPos;
public double yyPos;
public double xxVel;
public double yyVel;
public double mass;
public String imgFileName;

public BodyExtreme(double xP, double yP, double xV,
              double yV, double m, String img){
	xxPos = xP;
	yyPos = yP;
	xxVel = xV;
	yyVel = yV;
	mass = m;
	imgFileName = img;

	}

public BodyExtreme(BodyExtreme b){
	xxPos = b.xxPos;
	yyPos = b.yyPos;
	xxVel = b.xxVel;
	yyVel = b.yyVel;
	mass = b.mass;
	imgFileName = b.imgFileName;
}

public double calcDistance(BodyExtreme z){
	double dx;
	double dy;
	double r;

	dx = this.xxPos - z.xxPos;
	dy = this.yyPos - z.yyPos;
	r = Math.sqrt(dx*dx + dy*dy);

	return r; 

}

public double calcForceExertedBy(BodyExtreme t){
	double f;
	double g = 6.67e-11;
	double r_temp = calcDistance(t);

	if(this.equals(t)){
		f = 0;
	} else {
		f = (g*this.mass*t.mass)/(r_temp * r_temp);
	}

	return f; 

}

public double calcForceExertedByX(BodyExtreme s){
	double fx;
	double f_temp = calcForceExertedBy(s);
	double dx = s.xxPos - this.xxPos;
	double r_temp = calcDistance(s);

	fx = (f_temp*dx)/r_temp;

	return fx; 


}

public double calcForceExertedByY(BodyExtreme l){
	double fy;
	double f_temp = calcForceExertedBy(l);
	double dy = l.yyPos - this.yyPos;
	double r_temp = calcDistance(l);

	fy = (f_temp*dy)/r_temp;

	return fy; 


}

public double calcNetForceExertedByX(BodyExtreme[] allBodys){
	double net_fx = 0;

	for (BodyExtreme i : allBodys){
		if(!this.equals(i)){
			net_fx += calcForceExertedByX(i);
		}
	}

	return net_fx;

}

public double calcNetForceExertedByY(BodyExtreme[] allBodys){
	double net_fy = 0;

	for (BodyExtreme i : allBodys){
		if(!this.equals(i)){
			net_fy += calcForceExertedByY(i);
		}
	}

	return net_fy;

}

public void update(double dt, double fX, double fY){
	double ax = fX/this.mass;
	double ay = fY/this.mass;
	xxVel += dt * ax;
	yyVel += dt * ay;
	xxPos += dt * xxVel;
	yyPos += dt * yyVel;


}

public void draw(){
	StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	
}


}