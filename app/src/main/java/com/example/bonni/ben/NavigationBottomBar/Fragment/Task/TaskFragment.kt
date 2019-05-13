package com.example.bonni.ben.NavigationBottomBar.Fragment.Task


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter.AnimalAdapter
import com.example.bonni.ben.R
import kotlinx.android.synthetic.main.fragment_task.*


class TaskFragment : Fragment() {
  companion object {

    fun newInstance(): TaskFragment {
      return TaskFragment()
    }
  }
  val animals: ArrayList<String> = ArrayList()



  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater?.inflate(R.layout.fragment_task, container, false)

    // Loads animals into the ArrayList
    addAnimals()

    // Creates a vertical Layout Manager
    task_rv_mission.layoutManager = LinearLayoutManager(requireContext())

    // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

    // Access the RecyclerView Adapter and load the data into it
    task_rv_mission.adapter = AnimalAdapter(animals, requireContext())

  }

  // Adds animals to the empty animals ArrayList
  fun addAnimals() {
    animals.add("dog")
    animals.add("cat")
    animals.add("owl")
    animals.add("cheetah")
    animals.add("raccoon")
    animals.add("bird")
    animals.add("snake")
    animals.add("lizard")
    animals.add("hamster")
    animals.add("bear")
    animals.add("lion")
    animals.add("tiger")
    animals.add("horse")
    animals.add("frog")
    animals.add("fish")
    animals.add("shark")
    animals.add("turtle")
    animals.add("elephant")
    animals.add("cow")
    animals.add("beaver")
    animals.add("bison")
    animals.add("porcupine")
    animals.add("rat")
    animals.add("mouse")
    animals.add("goose")
    animals.add("deer")
    animals.add("fox")
    animals.add("moose")
    animals.add("buffalo")
    animals.add("monkey")
    animals.add("penguin")
    animals.add("parrot")
  }


}

