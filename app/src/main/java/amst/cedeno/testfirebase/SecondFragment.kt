package amst.cedeno.testfirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import amst.cedeno.testfirebase.databinding.FragmentSecondBinding
import android.renderscript.Sampler.Value
import android.widget.Switch
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private lateinit var databaseRef: DatabaseReference
    private lateinit var swBoolean: Switch
    private lateinit var txtBoolean: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        databaseRef = FirebaseDatabase.getInstance().getReference("")

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        swBoolean = _binding!!.swBoolean
        txtBoolean = _binding!!.txtBoolean

        swBoolean.setOnCheckedChangeListener { _, isChecked ->
            databaseRef.child("campoboolean").setValue(isChecked)
        }

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Leer los valores del snapshot
                val boolean = snapshot.child("campoboolean").getValue(Boolean::class.java)


                // Actualizar los TextViews con los valores obtenidos
                txtBoolean.text = "Campo boolean: ${boolean.toString() ?: "N/A"}"
                if (boolean != null) {
                    swBoolean.isChecked = boolean
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar errores
                println("Error al leer los datos: ${error.message}")
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}