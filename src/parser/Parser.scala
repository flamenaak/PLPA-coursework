package parser

import java.awt.Color

import GUI_Model.CustomTextArea
import model._

import util.control.Breaks._

class Parser(_input: String, errors: CustomTextArea) {

  val BOUNDING_BOX = "BOUNDING-BOX"
  val CIRCLE = "CIRCLE"
  val LINE = "LINE"
  val RECTANGLE = "RECTANGLE"
  val TEXT = "TEXT"
  val DRAW = "DRAW"
  val FILL = "FILL"
  val input = _input

  def parse(): Array[Command] = {
    if (input != null && input != "") {
      var parsed_commands = List[Command]()
      var split_array = input.split("\n")

      for (i <- 0 to (split_array.length - 1)) {
        //syntax check for command brackets
        var current_line = split_array(i)
        var syntax_correct = check_syntax_outer_parentheses(current_line)
        if (!syntax_correct) {
          errors.get_area().append("Syntax mistake, please encapsulate the command in parentheses!\n")
          break
        }
        val str = split_array(i).substring(1, split_array(i).length - 1)
        var parsed_command: DrawCommand = null
        str match {
          case s if s.startsWith(RECTANGLE) => {
            parsed_command = new DrawCommand(parse_rect(s))
          }
          case s if s.startsWith(LINE) => {
            parsed_command = new DrawCommand(parse_line(s))
          }
          case s if s.startsWith(BOUNDING_BOX) => {
            parsed_command = new DrawCommand(parse_b_box(s))
          }
          case s if s.startsWith(CIRCLE) => {
            parsed_command = new DrawCommand(parse_circle(s))
          }
          case s if s.startsWith(TEXT) => {
            parsed_command = new DrawCommand(parse_text(s))
          }
          case s if s.startsWith(FILL) => {
            var parsed_fill_command_list: List[Command] = parse_fill_draw(s, FILL)
            parsed_commands = parsed_commands ++ parsed_fill_command_list
          }
          case s if s.startsWith(DRAW) => {
            var parsed_draw_command_list: List[Command] = parse_fill_draw(s, DRAW)
            parsed_commands = parsed_commands ++ parsed_draw_command_list
          }
          case default => {
            errors.get_area().append(default + " is an unknown command\n")
          }
        }
        if (parsed_command != null) {
          parsed_commands = parsed_commands :+ parsed_command
        }
      }
      return parsed_commands.toArray
    }else{
      errors.get_area().append("You are executing empty command list.")
      return null
    }
  }

  def extract_coordinates(all_args: List[Int], axis: String): List[Int] = {
    axis match {
      case "x" => return all_args.zipWithIndex.filter(_._2 % 2 == 0).map(_._1)
      case "y" => return all_args.zipWithIndex.filter(_._2 % 2 == 1).map(_._1)
      case _ => null
    }

  }

  def check_syntax_outer_parentheses(line: String): Boolean = {
    if (line(0) != '(') {
      return false
    }
    var cur_len = line.length
    var stripped = line.substring(1, cur_len)
    if (stripped.startsWith(RECTANGLE) || stripped.startsWith(LINE) || stripped.startsWith(BOUNDING_BOX)) {

      if (stripped(stripped.length - 1) == ')' && stripped(stripped.length - 2) != ')') {
        return false
      }
    }

    if (stripped.startsWith(CIRCLE) || stripped.startsWith(TEXT) || stripped.startsWith(DRAW) || stripped.startsWith(FILL)) {
      if (stripped(0) != '(' && stripped(stripped.length - 1) != ')') {
        return false
      }
    }

    return true
  }


  def parse_fill_draw(line: String, what: String): List[Command] = {
    // strip outer parentheses
    var parsed_commands: List[Command] = List[Command]()
    var color = ""

    what match {
      case "FILL" => {
        // strip "FILL"
        var stripped_s = line.substring(FILL.length + 2, line.length)
        //strip inner parentheses
        stripped_s = stripped_s.substring(1, stripped_s.length - 2)
        var split_array = stripped_s.split("\"")
        for (i <- 0 to (split_array.length - 1)) {
          var current_object = split_array(i)
          if (current_object != " " && current_object != "") {
            var parsed_command: FillCommand = null
            current_object match {
              case s if s.startsWith("(") => {
                val str = split_array(i).substring(1, split_array(i).length - 1)
                str match {
                  case c if c.startsWith(RECTANGLE) => {
                    parsed_command = new FillCommand(parse_rect(c).setColor(getColorByName(color)).asInstanceOf[Fillable])
                  }
                  case c if c.startsWith(CIRCLE) => {
                    parsed_command = new FillCommand(parse_circle(c).setColor(getColorByName(color)).asInstanceOf[Fillable])
                  }
                  case c if c.startsWith(BOUNDING_BOX) => {
                    parsed_command = new FillCommand(parse_text(c).setColor(getColorByName(color)).asInstanceOf[Fillable])
                  }
                  case c if c.startsWith(TEXT) => {
                    errors.get_area().append("Error -> Text is not fillable.\n")
                    break
                  }
                  case c if c.startsWith(LINE) => {
                    errors.get_area().append("Error -> Line is not fillable.\n")
                    break
                  }
                }
              }
              case s => {
                //color
                color = s
              }
            }
            if (parsed_command != null) {
              parsed_commands = parsed_commands :+ parsed_command
            }
          }
        }

        return parsed_commands
      }
      case "DRAW" => {
        var stripped_s = line.substring(DRAW.length + 2, line.length)
        //strip inner parentheses
        stripped_s = stripped_s.substring(1, stripped_s.length - 2)
        var split_array = stripped_s.split("\"")
        var parsed_command: DrawCommand = null
        for (i <- 0 to (split_array.length - 1)) {
          var current_object = split_array(i)
          if (current_object != " " && current_object != "") {
            current_object match {
              case s if s.startsWith("(") => {
                val str = split_array(i).substring(1, split_array(i).length - 1)
                str match {
                  case c if c.startsWith(RECTANGLE) => {
                    parsed_command = new DrawCommand(parse_rect(c).setColor(getColorByName(color)))
                  }
                  case c if c.startsWith(LINE) => {
                    parsed_command = new DrawCommand(parse_line(c).setColor(getColorByName(color)))
                  }
                  case c if c.startsWith(CIRCLE) => {
                    parsed_command = new DrawCommand(parse_circle(c).setColor(getColorByName(color)))
                  }
                  case c if c.startsWith(BOUNDING_BOX) => {
                    parsed_command = new DrawCommand(parse_b_box(c).setColor(getColorByName(color)))
                  }
                  case c if c.startsWith(TEXT) => {
                    parsed_command = new DrawCommand(parse_text(c).setColor(getColorByName(color)))
                  }
                }
              }
              case s => {
                //color
                color = s
              }
            }
          }
          if (parsed_command != null) {
            parsed_commands = parsed_commands :+ parsed_command
          }
        }
        return parsed_commands
      }
    }
  }

  def parse_rect(arg: String): Drawable = {
    var stripped_s = arg.substring(RECTANGLE.length, arg.length)
    var syntax_correct: Boolean = check_syntax(stripped_s, RECTANGLE)
    if (!syntax_correct) {
      errors.get_area().append("Incorrect syntax @ " + RECTANGLE + " command\n")
      break
    }
    var parsed_args = ("""\d+""".r findAllIn stripped_s).toList.flatMap(_.toString.toIntOption)
    var x_args = extract_coordinates(parsed_args, "x")
    var y_args = extract_coordinates(parsed_args, "y")
    var p1 = new Point(x_args(0), y_args(0))
    var p2 = new Point(x_args(1), y_args(1))
    return new Rectangle(p1, p2)
  }

  def parse_line(arg: String): Drawable = {

    var stripped_s = arg.substring(LINE.length, arg.length)
    var syntax_correct: Boolean = check_syntax(stripped_s, LINE)
    if (!syntax_correct) {
      errors.get_area().append("Incorrect syntax @ " + LINE + " command\n")
      break
    }
    var parsed_args = ("""\d+""".r findAllIn stripped_s).toList.flatMap(_.toString.toIntOption)
    var x_args = extract_coordinates(parsed_args, "x")
    var y_args = extract_coordinates(parsed_args, "y")
    return new Line(new Point(x_args(0), y_args(0)), new Point(x_args(1), y_args(1)))
  }

  def parse_circle(arg: String): Drawable = {
    var stripped_s = arg.substring(CIRCLE.length, arg.length)
    var syntax_correct: Boolean = check_syntax(stripped_s, CIRCLE)
    if (!syntax_correct) {
      errors.get_area().append("Incorrect syntax @ " + CIRCLE + " command - missing a space most likely\n")
      break
    }
    var parsed_args = ("""\d+""".r findAllIn stripped_s).toList.flatMap(_.toString.toIntOption)
    var x = parsed_args(0)
    var y = parsed_args(1)
    var radius = parsed_args(2)
    return new Circle(new Point(x, y), radius)
  }

  def parse_b_box(arg: String): Drawable = {
    var stripped_s = arg.substring(BOUNDING_BOX.length, arg.length)
    var syntax_correct: Boolean = check_syntax(stripped_s, BOUNDING_BOX)
    if (!syntax_correct) {
      errors.get_area().append("Incorrect syntax @ " + BOUNDING_BOX + " command - missing a space most likely\n")
      break
    }
    var parsed_args = ("""\d+""".r findAllIn stripped_s).toList.flatMap(_.toString.toIntOption)
    var x_args = extract_coordinates(parsed_args, "x")
    var y_args = extract_coordinates(parsed_args, "y")
    return new BoundingBox(new Point(x_args(0), y_args(0)), new Point(x_args(1), y_args(1)))
  }

  def parse_text(arg: String): Drawable = {
    var stripped_s = arg.substring(TEXT.length, arg.length)
    var syntax_correct: Boolean = check_syntax(stripped_s, TEXT)
    if (!syntax_correct) {
      errors.get_area().append("Incorrect syntax @" + TEXT + " command - missing a space most likely\n")
      break
    }
    var parsed_args = ("""\d+""".r findAllIn stripped_s).toList.flatMap(_.toString.toIntOption)
    var x = parsed_args(0)
    var y = parsed_args(1)
    var text = stripped_s.split("\"")(1)
    return new Text(new Point(x, y), text)
  }

  def check_syntax(stripped_s: String, check_what: String): Boolean = {
    var a: Boolean = stripped_s(1) == '(' || stripped_s(5) == ')'
    if (check_what == RECTANGLE || check_what == BOUNDING_BOX || check_what == LINE) {
      var b: Boolean = stripped_s(7) == '(' || stripped_s(11) == ')'
      if (!a) {
        errors.get_area().append("Error, (  or ) missing in the first point!\n")
      }
      if (!b) {
        errors.get_area().append("Error, (  or ) missing in the second point!\n")
      }
      return a && b
    }

    if (check_what == CIRCLE || check_what == TEXT) {
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
