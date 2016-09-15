package ca.ece.ubc.cpen221.mp5.Query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.*;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;



public class QueryFactory {

	public static Set<Restaurant> parse(String string) {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(string);
        QueryLexer lexer = new QueryLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        // Feed the tokens into the parser.
        QueryParser parser = new QueryParser(tokens);
        parser.reportErrorsAsExceptions();
        
        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.orExpr(); // "root" is the starter rule.
        
        // debugging option #1: print the tree to the console
 //       System.err.println(tree.toStringTree(parser));

        // debugging option #2: show the tree in a window
//        ((RuleContext)tree).inspect(parser);

        // debugging option #3: walk the tree with a listener
 //       new ParseTreeWalker().walk(new QueryListener_PrintEverything(), tree);
        
        // Finally, construct a Document value by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        QueryListener_QueryCreator listener = new QueryListener_QueryCreator();
        walker.walk(listener, tree);
        
        Set<Restaurant> queryResult = new HashSet<>();
        ArrayList<Restaurant> result = listener.findRestaurants();
        queryResult.addAll(result);
        // return the Document value that the listener created
        return new HashSet<Restaurant>(queryResult);
    }
	
	private static class QueryListener_QueryCreator extends QueryBaseListener{
		private Stack<Query> stack  = new Stack<Query>(); 
		
		@Override
		public void exitIn(QueryParser.InContext ctx){
			if(ctx.IN()!=null){
				String neighborhood = ctx.STRING().getText();
				String newString = neighborhood.substring(1, neighborhood.length()-1);
				stack.push(new InAtom(newString));
			}
		}
		
		@Override
		public void exitCategory(QueryParser.CategoryContext ctx){
			if(ctx.CATEGORY()!=null){
				String category = ctx.STRING().getText();
				String newString = category.substring(1, category.length()-1);
				stack.push(new CategoriesAtom(newString));
			}
		}
		
		@Override
		public void exitName(QueryParser.NameContext ctx){
			if(ctx.NAME()!=null){
				String name = ctx.STRING().getText();
				String newString = name.substring(1, name.length()-1);
				stack.push(new InAtom(newString));
			}
		}
		
		@Override
		public void exitRating(QueryParser.RatingContext ctx){
			if(ctx.RATING()!=null){
				int lower = Integer.parseInt(ctx.range().leftnum().getText());
				int upper = Integer.parseInt(ctx.range().rightnum().getText());
				stack.push(new RatingAtom(lower, upper));
			}
		}
		
		@Override
		public void exitPrice(QueryParser.PriceContext ctx){
			if(ctx.PRICE()!=null){
				int lower = Integer.parseInt(ctx.range().leftnum().getText());
				int upper = Integer.parseInt(ctx.range().rightnum().getText());
				stack.push(new PriceAtom(lower, upper));
			}
		}
		
		@Override
		public void exitAndExpr(QueryParser.AndExprContext ctx){
			if(ctx.AND()!=null){
				int size = ctx.atom().size();
				ArrayList<Atom> atoms = new ArrayList<>();
				
				for(int i = 0; i <= size; i++){
					Query q = stack.pop();
					Atom atom = (Atom)q;
					atoms.add(atom);
				}
				
				stack.push(new AndExpr(atoms));
			}
		}
		
		@Override
		public void exitOrExpr(QueryParser.OrExprContext ctx){
			if(ctx.OR()!=null){
				int size = ctx.andExpr().size();
				ArrayList<AndExpr> andExpr = new ArrayList<>();
				
				for(int i = 0; i <= size; i++){
					Query q = stack.pop();
					AndExpr a = (AndExpr)q;
					andExpr.add(a);
				}
				
				stack.push(new OrExpr(andExpr));
			}
		}
		
		private ArrayList<Restaurant> findRestaurants(){
			ArrayList<Restaurant> searchResult;
			OrExpr expr = (OrExpr)stack.pop();
			searchResult = expr.search();
			return new ArrayList<Restaurant> (searchResult);
		}
	}

	private static RestaurantDB database = new RestaurantDB("data/restaurants.json",
	        "data/reviews.json", "data/users.json");
	private static Map<String, Restaurant> restaurants = database.getRestaurantMap();
	
	private static class InAtom extends Atom{
		private String neighborhood;
		
		private InAtom(String neighborhood){
			this.neighborhood = neighborhood;
		}
		
		@Override
		public ArrayList<Restaurant> search(){
			ArrayList<Restaurant> result = new ArrayList<>();
			
			for(Restaurant r : restaurants.values()){
				if (r.getNeighborhoods().contains(neighborhood)){
					result.add(r);
				}
			}
			return result;
		}
	}
	
	private static class CategoriesAtom extends Atom{
		private String category;
		
		private CategoriesAtom(String category){
			this.category = category;
		}
		
		@Override
		public ArrayList<Restaurant> search(){
			ArrayList<Restaurant> result = new ArrayList<>();
			
			for(Restaurant r : restaurants.values()){
				if (r.getCategories().contains(category)){
					result.add(r);
				}
			}
			return result;
		}
	}
	
	private static class NameAtom extends Atom{
		private String name;
		
		private NameAtom(String name){
			this.name = name;
		}
		
		@Override
		public ArrayList<Restaurant> search(){
			ArrayList<Restaurant> result = new ArrayList<>();
			
			for(Restaurant r : restaurants.values()){
				if (r.getName().equals(name)){
					result.add(r);
				}
			}
			return result;
		}
	}
	
	private static class PriceAtom extends Atom{
		private int lowerPrice;
		private int higherPrice;
		
		private PriceAtom(int lowerPrice, int higherPrice){
			this.lowerPrice = lowerPrice;
			this.higherPrice = higherPrice;
		}
		
		@Override
		public ArrayList<Restaurant> search(){
			ArrayList<Restaurant> result = new ArrayList<>();
			
			for(Restaurant r : restaurants.values()){
				if (r.getPrice() >= lowerPrice && r.getPrice() <= higherPrice){
					result.add(r);
				}
			}
			return result;
		}
	}
	
	private static class RatingAtom extends Atom{
		private int lowerRating;
		private int higherRating;
		
		private RatingAtom(int lowerRating, int higherRating){
			this.lowerRating = lowerRating;
			this.higherRating = higherRating;
		}
		
		@Override
		public ArrayList<Restaurant> search(){
			ArrayList<Restaurant> result = new ArrayList<>();
			
			for(Restaurant r : restaurants.values()){
				if (r.getRating() >= lowerRating && r.getRating() <= higherRating){
					result.add(r);
				}
			}
			return result;
		}
	}
	
}
