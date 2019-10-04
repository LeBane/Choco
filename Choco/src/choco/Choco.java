/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choco;
import static choco.Choco.*;
import java.util.Scanner;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.extension.TuplesFactory;
import org.chocosolver.solver.variables.IntVar;

/**
 *
 * @author benjamin
 */
public class Choco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Model model1 = new Model("Exo1 piece");
        // On fixe nos constante du nombre de piece de base disponible
        int E2 = 10;
        int E1 = 10;
        int C50 = 10;
        int C20 = 10;
        int C10 = 10;
        // Et nos valeur T(montant inseré) et P(prix)
        //Pour l'instant en constante, mais ce sera possiblement des valeurs variables entrée par l'utilisateur via un scanner(system.in)
        int T = 180; // En centimes 1,80euro
        int P = 130;
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer l'argent en centime que vous donnez");
        T = sc.nextInt();
        System.out.println("Entrer le prix en centime de la boisson");
        P = sc.nextInt();
        int c = T-P;
        //On a nos constante
        // Maintenant il faut créer nos variables, qui sont le nombre de piece que l'on rend
        IntVar e2r = model1.intVar("e2r", 0, E2);
        IntVar e1r = model1.intVar("e1r", 0, E1);
        IntVar c50r = model1.intVar("c50r", 0, C50);
        IntVar c20r = model1.intVar("c20r", 0, C20);
        IntVar c10r = model1.intVar("c10r", 0, C10);
        IntVar nbPieceRendu = model1.intVar("NbPieceRendu",0,10);
        
        
        model1.scalar(new IntVar[] {e2r,e1r,c50r,c20r,c10r}, new int[]{200,100,50,20,10},"=",c).post(); // Doc de la fonction :It ensures that sum(VARS[i]*COEFFS[i]) OPERATOR SCALAR
        // Donc ce que l'on cherche. T-P = e2r *200 + e1r * 100 ......
        model1.sum(new IntVar[] {e2r,e1r,c50r,c20r,c10r}, "=", nbPieceRendu).post();
        
        
        Solver solver = model1.getSolver();
        Solution sol = solver.findOptimalSolution(nbPieceRendu, false);
        if(sol != null){
            System.out.println(sol.toString());
        }
        
        /*
        System.out.println("Nb de piece 2euro : "+model.getVar(0) + " , nb piece de 1 euro : "+model.getVar(1) + " , 50 cts : " +model.getVar(2)
                + " ,20cts : "+model.getVar(3) + " , 10 cts " +model.getVar(4) + " et la solution optimal du nombre de peice est : " +model.getVar(5));
        */
       
    }
    
}
