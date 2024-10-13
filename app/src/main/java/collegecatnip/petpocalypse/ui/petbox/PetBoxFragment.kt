package collegecatnip.petpocalypse.ui.petbox

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import collegecatnip.petpocalypse.R

class PetBoxFragment : Fragment() {

    companion object {
        fun newInstance() = PetBoxFragment()
    }

    private val viewModel: PetBoxViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_pet_box, container, false)
    }

}