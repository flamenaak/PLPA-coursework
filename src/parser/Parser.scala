package parser
import java.awt.Color

import model._

import util.control.Breaks._
class Parser(_input:String){

  val BOUNDING_BOX = "BOUNDING BOX"
  val CIRCLE = "CIRCLE"
  val LINE = "LINE"
  val RECTANGLE = "RECTANGLE"
  val TEXT = "TEXT"
  val DRAW = "DRAW"
  val FILL = "FILL"
  val input = _input

  def parse() : Array[Command] =  {

    var parsed_commands = List[Command]()

    var split_array = input.split("\n")

    for ( i <- 0 to (split_array.length - 1) ) {

      //syntax check for command brackets
      var current_line = split_array(i)
      var syntax_correct = check_syntax_outer_parentheses(current_line)
      if(!syntax_correct){
        println("Syntax mistake, please encapsulate the command in parentheses!")
        break
      }

      val str = split_array(i).substring(1, split_array(i).length - 1)

      str match {
        case s if s.startsWith(RECTANGLE) => {
          var parsed_rect : DrawCommand = new DrawCommand(parse_rect(s))
          parsed_commands = parsed_commands :+ parsed_rect
        }
        case s if s.startsWith(LINE) => {
          var parsed_line : DrawCommand = new DrawCommand(parse_line(s))
          parsed_commands = parsed_commands :+ parsed_line
        }
        case s if s.startsWith(BOUNDING_BOX) => {
          var parsed_b_box : DrawCommand = new DrawCommand(parse_b_box(s))
          parsed_commands = parsed_commands :+ parsed_b_box
        }
        case s if s.startsWith(CIRCLE) => {
          var parsed_circle : DrawCommand = new DrawCommand(parse_circle(s))
          parsed_commands = parsed_commands :+ parsed_circle
        }
        case s if s.startsWith(TEXT) => {
          var parsed_text : DrawCommand = new DrawCommand(parse_text(s))
          parsed_commands = parsed_commands :+ parsed_text
        }
        case s if s.startsWith(FILL) => {
          var parsed_fill_command_list : List[Command] = parse_fill_draw(s,FILL)
          parsed_commands = parsed_commands ++ parsed_fill_command_list

        }
        case s if s.startsWith(DRAW) => {
          var parsed_draw_command_list : List[Command] = parse_fill_draw(s,DRAW)
          parsed_commands = parsed_commands ++ parsed_draw_command_list
        }

        case _ => println("wrong bitch")
      }
    }

    //TODO
    // convert objects to commands
    return parsed_commands.toArray
  }

  def extract_coordinates(all_args:List[Int],axis:String) : List[Int] = {
    axis match {
      case "x" =>   return all_args.zipWithIndex.filter(_._2 % 2 == 0).map(_._1)
      case "y" => return all_args.zipWithIndex.filter(_._2 % 2 == 1).map(_._1)
      case _ => null
    }

  }

  def check_syntax_outer_parentheses(line:String): Boolean ={
    if(line(0) != '('){
      return false
    }
    var cur_len = line.length
    var stripped = line.substring(1,cur_len)
    if(stripped.startsWith(RECTANGLE) ||stripped.startsWith(LINE) ||stripped.startsWith(BOUNDING_BOX)){

      if(stripped(stripped.length-1) == ')' && stripped(stripped.length-2) != ')'){
        return false
      }
    }

    if(stripped.startsWith(CIRCLE) || stripped.startsWith(TEXT) || stripped.startsWith(DRAW) || stripped.startsWith(FILL)){
      if(stripped(0) != '(' && stripped(stripped.length-1) != ')'){
        return false
      }
    }

    return true
  }

  def parse_fill_draw(line:String,what:String): List[Command] = {
    // strip outer parentheses

    var parsed_commands : List[Command] = List[Command]()
    var color = ""

    val str = line.substring(1, line.length - 1)
    what match {
      case "FILL" => {
        // strip "FILL"
        var stripped_s = line.substring(FILL.length+2,line.length)
        //strip inner parentheses
        stripped_s = stripped_s.substring(1,stripped_s.length - 2)
        var index = 0
        var split_array = stripped_s.split("\"")
        for ( i <- 0 to (split_array.length - 1) ) {
          if(split_array(i) != " " && split_array(i) != ""){
            var arg = split_array(i)
            arg match {
              case s if s.startsWith("(") => {
                val str = split_array(i).substring(1, split_array(i).length - 1)
                str match {
                  case c if c.startsWith(RECTANGLE) => {
                    var parsed_rect_drawable = parse_rect(c).setColor(getColorByName(color))

                    var parsed_rect_command : FillCommand = new FillCommand(parsed_rect_drawable.asInstanceOf[FillAble])
                    parsed_commands = parsed_commands :+ parsed_rect_command
                  }
                  case c if c.startsWith(CIRCLE) => {
                    var parsed_circle_drawable = parse_circle(c).setColor(getColorByName(color))
                    var parsed_circle_command : FillCommand = new FillCommand(parsed_circle_drawable.asInstanceOf[FillAble])
                    parsed_commands = parsed_commands :+ parsed_circle_command
                  }
                  case c if c.startsWith(BOUNDING_BOX) => {
                    var parsed_text_drawable = parse_text(c).setColor(getColorByName(color))
                    var parsed_b_box_command : FillCommand = new FillCommand(parsed_text_drawable.asInstanceOf[FillAble])
                    parsed_commands = parsed_commands :+ parsed_b_box_command
                  }
                  case c if c.startsWith(TEXT) => {
                    /*var parsed_text : FillCommand = new FillCommand(parse_text(c))
                    parsed_commands = parsed_commands :+ parsed_text*/
                    println("Error -> Text is not fillable.")
                    break
                  }
                  case c if c.startsWith(LINE) => {
                  /*  var parsed_line_command : FillCommand = new FillCommand(parse_line(c))
                    parsed_commands = parsed_commands :+ parsed_line_command*/
                    println("Error -> Line is not fillable")
                    break
                  }
                }
              }
              case s => {
                //color
                color = s
              }
            }
          }
        }
//        for(c <- parsed_commands){
//          var command = c.asInstanceOf[FillCommand]
//          command.setColor(getColorByName(color))
//        }
        return parsed_commands
        //TODO
        // RETURN ARRAY OF DRAWCOMMANDS/FILLCOMMANDS
      }
      case "DRAW" => {
        var stripped_s = line.substring(DRAW.length+2,line.length)
        //strip inner parentheses
        stripped_s = stripped_s.substring(1,stripped_s.length - 2)
        var index = 0
        var split_array = stripped_s.split("\"")
        for ( i <- 0 to (split_array.length - 1) ) {
          if(split_array(i) != " " && split_array(i) != ""){
            var arg = split_array(i)
            arg match {
              case s if s.startsWith("(") => {
                val str = split_array(i).substring(1, split_array(i).length - 1)
                str match {
                  case c if c.startsWith(RECTANGLE) => {
                    var parsed_drawable_rect = parse_rect(c).setColor(getColorByName(color))
                    var parsed_rect : DrawCommand = new DrawCommand(parsed_drawable_rect)
                    parsed_commands = parsed_commands :+ parsed_rect
                  }
                  case c if c.startsWith(LINE) => {
                    var parsed_drawable_line = parse_line(c).setColor(getColorByName(color))
                    var parsed_line : DrawCommand = new DrawCommand(parsed_drawable_line)
                    parsed_commands = parsed_commands :+ parsed_line
                  }
                  case c if c.startsWith(CIRCLE) => {
                    var parsed_drawable_circle= parse_circle(c).setColor(getColorByName(color))
                    var parsed_circle : DrawCommand = new DrawCommand(parsed_drawable_circle)
                    parsed_commands = parsed_commands :+ parsed_circle
                  }
                  case c if c.startsWith(BOUNDING_BOX) => {
                    var parsed_drawable_b_box= parse_b_box(c).setColor(getColorByName(color))
                    var parsed_b_box : DrawCommand = new DrawCommand(parsed_drawable_b_box)
                    parsed_commands = parsed_commands :+ parsed_b_box
                  }
                  case c if c.startsWith(TEXT) => {
                    var parsed_drawable_text= parse_text(c).setColor(getColorByName(color))
                    var parsed_text : DrawCommand = new DrawCommand(parsed_drawable_text)
                    parsed_commands = parsed_commands :+ parsed_text
                  }
                }
              }
              case s => {
                //color
                color = s
              }
            }
          }
        }

//        for(c <- parsed_commands){
//          var command = c.asInstanceOf[DrawCommand]
//          command.setColor(getColorByName(color))
//        }
        return parsed_commands
      }
    }



  }

  def parse_rect(arg:String) : Drawable = {
    var stripped_s = arg.substring(RECTANGLE.length,arg.length)
    var syntax_correct : Boolean = check_syntax(stripped_s,RECTANGLE)
    if(!syntax_correct){
      println("Incorrect syntax @" + RECTANGLE + " command")
      break
    }

    var parsed_args = ("""\d+""".r findAllIn stripped_s).toList.flatMap(_.toString.toIntOption)
    var x_args = extract_coordinates(parsed_args,"x")
    var y_args = extract_coordinates(parsed_args,"y")
    var p1 = new Point(x_args(0),y_args(0))
    var p2 = new Point(x_args(1),y_args(1))
    return new Rectangle(p1,p2)
  }

  def parse_line(arg:String) : Drawable = {

    var stripped_s = arg.substring(LINE.length,arg.length)
    var syntax_correct : Boolean = check_syntax(stripped_s,LINE)
    if(!syntax_correct){
      println("Incorrect syntax @" + LINE + " command")
      break
    }
    var parsed_args = ("""\d+""".r findAllIn stripped_s).toList.flatMap(_.toString.toIntOption)
    var x_args = extract_coordinates(parsed_args,"x")
    var y_args = extract_coordinates(parsed_args,"y")

    var p1 = new Point(x_args(0),y_args(0))
    var p2 = new Point(x_args(1),y_args(1))

    return new Line(p1,p2)
  }

  def parse_circle(arg:String) : Drawable = {
    var stripped_s = arg.substring(CIRCLE.length,arg.length)
    var syntax_correct : Boolean = check_syntax(stripped_s,CIRCLE)
    if(!syntax_correct){
      println("Incorrect syntax @" + CIRCLE + " command")
      break
    }
    var parsed_args = ("""\d+""".r findAllIn stripped_s).toList.flatMap(_.toString.toIntOption)
    var x = parsed_args(0)
    var y = parsed_args(1)
    var radius = parsed_args(2)
    var point = new Point(x,y)
    return new Circle(point,radius)
  }

  def parse_b_box(arg:String) : Drawable = {
    var stripped_s = arg.substring(BOUNDING_BOX.length,arg.length)
    var syntax_correct : Boolean = check_syntax(stripped_s,BOUNDING_BOX)
    if(!syntax_correct){
      println("Incorrect syntax @" + BOUNDING_BOX + " command")
      break
    }
    var parsed_args = ("""\d+""".r findAllIn stripped_s).toList.flatMap(_.toString.toIntOption)
    var x_args = extract_coordinates(parsed_args,"x")
    var y_args = extract_coordinates(parsed_args,"y")
    var p1 = new Point(x_args(0),y_args(0))
    var p2 = new Point(x_args(1),y_args(1))
    return new BoundingBox(p1,p2)
  }

  def parse_text(arg:String) : Drawable = {
    var stripped_s = arg.substring(TEXT.length,arg.length)
    var syntax_correct : Boolean = check_syntax(stripped_s,TEXT)
    if(!syntax_correct){
      println("Incorrect syntax @" + TEXT + " command")
      break
    }
    var parsed_args = ("""\d+""".r findAllIn stripped_s).toList.flatMap(_.toString.toIntOption)
    var x = parsed_args(0)
    var y = parsed_args(1)
    var text = stripped_s.split("\"")(1)
    var p = new Point(x,y)
    return new Text(p,text)
  }

  def check_syntax(stripped_s:String, check_what:String): Boolean ={

    var a: Boolean = stripped_s(1) == '(' || stripped_s(5) == ')'

    if(check_what == RECTANGLE || check_what == BOUNDING_BOX || check_what == LINE){

      var b: Boolean = stripped_s(7) == '(' || stripped_s(11) == ')'
      if(!a){
        println("Error, (  or ) missing in the first point!")
      }

      if(!b){
        println("Error, ( or ) missing in the second point!")
      }
      return a && b
    }

    if(check_what == CIRCLE || check_what == TEXT ){

      return a
    }

    return false

  }

  def getColorByName(name: String): Color = try classOf[Color].getField(name.toUpperCase).get(null).asInstanceOf[Color]
  catch {
    case e@(_: IllegalArgumentException | _: IllegalAccessException | _: NoSuchFieldException | _: SecurityException) =>
      e.printStackTrace()
      null
  }

}
