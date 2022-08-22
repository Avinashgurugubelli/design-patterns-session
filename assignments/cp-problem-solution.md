# Cartesian product problem solution (visitor pattern)

``` C#
using System;
using System.Collections.Generic;

//Object Structure
    public class Image{

        public List<Shape> structure= new List<Shape>();
        public void plot(List<ShapePoltter> plotters){
        
        //M*N cartesian product
            foreach(Shape s in structure){
                foreach(ShapePoltter p in plotters){
                    s.plot(p);
                }
            }
        }
        
    }

    public abstract class Shape{
        public abstract void plot(ShapePoltter plotter);
    }

    public class Rectangle:Shape{
        
        public string GeHeightAndWidth(){ return "R.H.W";}
        public override void plot(ShapePoltter plotter) {
            plotter.plot(this);
        }
        
    }
    public class Circle:Shape{
        public string GetRadius(){ return "C.R";}
        public override void plot(ShapePoltter plotter) {
            plotter.plot(this);
        }
    }
    public class Polygon:Shape{
        public string GetSides(){ return "P.S";}
        public override void plot(ShapePoltter plotter) {
            plotter.plot(this);
        }
    }

    public abstract class ShapePoltter{
        public abstract void plot(Rectangle shape);
        public abstract void plot(Circle shape);
        public abstract void plot(Polygon shape);
            
    }
        

    public class LaserPrinter : ShapePoltter{
        public override void plot(Rectangle shape) {
            Console.WriteLine("Drawing Rectangle shape using LaserPrinter, Rectangle parameters: " +  shape.GeHeightAndWidth());
        }
        public override void plot(Circle shape) {
            Console.WriteLine("Drawing Circle shape using LaserPrinter, Circle parameters: " +  shape.GetRadius());
        }
        public override void plot(Polygon shape){
            Console.WriteLine("Drawing Polygon shape using LaserPrinter, Polygon parameters: " +  shape.GetSides());
        }
    }

    public class InkJetrinter : ShapePoltter{
        public override void plot(Rectangle shape) {
            Console.WriteLine("Drawing Rectangle shape using InkJetrinter, Rectangle parameters: " +  shape.GeHeightAndWidth());
        }
        public override void plot(Circle shape) {
            Console.WriteLine("Drawing Circle shape using InkJetrinter, Circle parameters: " +  shape.GetRadius());
        }
        public override void plot(Polygon shape){
            Console.WriteLine("Drawing Polygon shape using InkJetrinter, Polygon parameters: " +  shape.GetSides());
        }
    }

    public class Program {
        
        public static void Main() {
            Shape rect = new Rectangle();
            Shape circle = new Circle();
            Shape polygon = new Polygon();
            
            ShapePoltter laserPrinter = new LaserPrinter();
            ShapePoltter inkJetrinter = new InkJetrinter();
            
            Image image = new Image();
            
            image.structure.Add(rect);
            image.structure.Add(circle);
            image.structure.Add(polygon);
            
            List<ShapePoltter> plotters = new List<ShapePoltter>();
            plotters.Add(laserPrinter);
            plotters.Add(inkJetrinter);
            
            // plotting all shapes
            image.plot(plotters);
        }
    }
```
