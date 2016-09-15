package ca.ece.ubc.cpen221.mp5.Query;

import java.util.ArrayList;
import java.util.Stack;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class AndExpr implements Query {

	private ArrayList<Atom> atoms;
	public AndExpr(ArrayList<Atom> atom) {
		// TODO Auto-generated constructor stub
		this.atoms = atom;
	}

	@Override
	public ArrayList<Restaurant> search() {
		// TODO Auto-generated method stub
		
		Stack<ArrayList<Restaurant>> stack = new Stack<>();
		ArrayList<Restaurant> finalResult = new ArrayList<Restaurant>();
		ArrayList<Restaurant> array1;
		ArrayList<Restaurant> array2;
		
		for(Atom atom : atoms){
			stack.push(atom.search());
		}
		while(!stack.isEmpty()){
			array1 = stack.pop();
			if(stack.isEmpty()){
				finalResult = array1;
				break;
			}else{
				array2 = stack.pop();
				stack.push(andEval(array1, array2));
			}
		}
		
		return new ArrayList<Restaurant>(finalResult);
	}
	
	private ArrayList<Restaurant> andEval (ArrayList<Restaurant> array1, ArrayList<Restaurant> array2){
		ArrayList<Restaurant> commonRestaurants = new ArrayList<Restaurant>();
		
		for(Restaurant r : array1){
			for(Restaurant r2 : array2){
				if(r.getBusinessID().equals(r2.getBusinessID())){
					commonRestaurants.add(r);
				}
			}
		}
		
		return new ArrayList<Restaurant>(commonRestaurants);
	}

}
