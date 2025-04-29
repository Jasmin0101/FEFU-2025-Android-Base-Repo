 package co.feip.fefu2025.presentation.ui.pages

import LoadingHomePage
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.navigation.DefaultNavigator
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.presentation.ui.states.home_page.HomePageState
import co.feip.fefu2025.presentation.ui.states.home_page.ui.ErrorHomePage
import co.feip.fefu2025.presentation.viewmodel.RepositoriesViewModel
import org.koin.androidx.compose.koinViewModel

 @RequiresApi(Build.VERSION_CODES.O)
 @Composable
 fun HomePage(viewModel: RepositoriesViewModel = koinViewModel()) {
     var searchQuery by remember { mutableStateOf("") }
     Scaffold(
         topBar = {

             Column(
                 modifier = Modifier
                     .fillMaxWidth()
                     .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                     .padding(vertical = 12.dp, horizontal = 16.dp, ),
                 verticalArrangement = Arrangement.spacedBy(8.dp),
             ) {
                 Spacer(Modifier.height(10.dp))
                 Row(
                     modifier = Modifier.fillMaxWidth(),
                     horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                     OutlinedTextField(
                         value = searchQuery,

                         onValueChange = {  },
                         readOnly = true,
                         placeholder = { Text("Search...") },

                         modifier = Modifier
                             .weight(1f)
                             .clip(RoundedCornerShape(16.dp))
                             .border(
                                 width = 2.dp,
                                 brush = Brush.linearGradient(
                                     colors = listOf(Color(0xffbf02b3), Color(0xffd18006)),
                                 ),
                                 shape = RoundedCornerShape(16.dp),
                             )
                             .background(color = Color(0xffffffff)) .onFocusChanged {
                                 focusState ->
                                 if (focusState.isFocused)
                                    viewModel.navigateSearchScreen()
                                 },
                         singleLine = true,
                     )
                 }
             }
         },
     ) { paddingValues ->

     when (val state = viewModel.homePageState) {
         is HomePageState.Loading -> {
             LoadingHomePage(viewModel, paddingValues)
         }
         is HomePageState.Loaded -> {
             LoadedHomePage(viewModel, paddingValues )
         }
         is HomePageState.Error -> {
          ErrorHomePage(viewModel, paddingValues)
         }
     }
     }
 }
 @RequiresApi(Build.VERSION_CODES.O)
 @Preview
 @Composable
 private fun PreviewHomePage() {
     HomePage(
         viewModel = RepositoriesViewModel(
             getRepositoriesUseCase = GetRepositoriesUseCase(
                 repository = RepositoryRepository()
             ),
             navigator = DefaultNavigator(
                 startDestination = Destination.BaseGraph
             )
         )
     )
 }
