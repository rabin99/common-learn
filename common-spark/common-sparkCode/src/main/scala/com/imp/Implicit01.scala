package com.imp

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/09/27
  */

class SpecialPerson(val name: String)

class Student(val name: String)

class Older(val name: String)

class Teacher(val name: String)

object Implicit0 {
    implicit def object2SecialPerson(obj: Object):SpecialPerson = {
      if(obj.getClass ==classOf[Student]){
        val student = obj.asInstanceOf[Student]
        new SpecialPerson(student.name)
      }else if(obj.getClass == classOf[Older]){
        val older = obj.asInstanceOf[Older]
        new SpecialPerson(older.name)
      }else{
        null
      }
    }
}
