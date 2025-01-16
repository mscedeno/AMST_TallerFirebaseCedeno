package amst.tallerfirebase.cedeno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import amst.tallerfirebase.cedeno.databinding.FragmentFirstBinding
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private lateinit var databaseRef: DatabaseReference
    private lateinit var textViewCampo1: TextView
    private lateinit var buttonEnviar: Button
    private lateinit var editTextVal: EditText

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        databaseRef = FirebaseDatabase.getInstance().getReference("")

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewCampo1 =  _binding!!.textViewCampo1
        editTextVal =  _binding!!.editTextValor

        var isChecked = false

        binding.btnEnviar.setOnClickListener { //lo que se realiza al presionar el boton
            databaseRef.child("campoboton").setValue(true) //cambia a true la variable campoboton de la database
            databaseRef.child("campo1").setValue(editTextVal.text.toString()) //obtiene el valor del EditText para enviarlo a la variable campo1 de la database
        }

        // Escuchar los datos en tiempo real
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Leer los valores del snapshot y lo agrega al textView de la app
                val campo1 = snapshot.child("campo1").getValue(String::class.java) //se cambio Int por String porque asi se definió en la database

                // Actualizar los TextViews con los valores obtenidos
                textViewCampo1.text = "Campo 1: ${campo1 ?: "N/A"}"
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