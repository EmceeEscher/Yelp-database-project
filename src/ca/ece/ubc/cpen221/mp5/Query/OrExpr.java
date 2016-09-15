package ca.ece.ubc.cpen221.mp5.Query;

import java.util.ArrayList;
import java.util.Stack;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class OrExpr implements Query {

	private ArrayList<AndExpr> andExprs;
	
	public OrExpr(ArrayList<AndExpr> andExprs) {
		// TODO Auto-generated constructor stub
		this.andExprs = andExprs;
	}

	@Override
	public ArrayList<Restaurant> search() {
		// TODO Auto-generated method stub
		Stack<ArrayList<Restaurant>> stack = new Stack<>();
		ArrayList<Restaurant> result = new ArrayList<>();
		ArrayList<Restaurant> array1;
		ArrayList<Restaurant> array2;
		
		for(AndExpr a : andExprs){
			stack.push(a.search());
		}
		
		while(!stack.isEmpty()){
			array1 = stack.pop();
			if(stack.isEmpty()){
				result = array1;
				break;
			}else{
				array2 = stack.pop();
				stack.push(OrEval(array1, array2));
			}
		}
		
		return new ArrayList<Restaurant>(result);
	}
	
	private ArrayList<Restaurant> OrEval(ArrayList<Restaurant> array1, ArrayList<Restaurant> array2){
		
		ArrayList<Restaurant> finalResult = new ArrayList<>();
		ArrayList<String> business_IDs = new ArrayList<>();
		
		for(Restaurant r : array1){
			business_IDs.add(r.getBusinessID());
			finalResult.add(r);
		}
		for(String s : business_IDs){
			for(Restaurant r2 : array2){
				if(!business_IDs.contains(r2.getBusinessID())){
					business_IDs.add(r2.getBusinessID());
					finalResult.add(r2);
				}
			}
		}
		
		return new ArrayList<Restaurant>(finalResult);
		
	}

}
