package com.logbook.snackstats.ui.screens.addfoodlog

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.logbook.snackstats.data.models.MealType
import com.logbook.snackstats.ui.components.BasicButton
import com.logbook.snackstats.ui.components.DatePickerTextField
import com.logbook.snackstats.ui.components.DescriptionTextField
import com.logbook.snackstats.ui.components.DropdownTextField
import com.logbook.snackstats.ui.components.TimePickerTextField
import com.logbook.snackstats.ui.theme.SnackStatsTheme
import org.koin.java.KoinJavaComponent.inject


@Preview
@Composable
private fun AddFoodLogScreenPrev() {
    SnackStatsTheme {
         AddFoodLogScreen(modifier = Modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddFoodLogScreen(
    modifier: Modifier
) {
    val viewModel : AddFoodLogViewModel by inject(AddFoodLogViewModel::class.java)
    var isSheetOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val imageUri by viewModel.selectedImageUri.collectAsState()

    val bottomSheetState = rememberModalBottomSheetState()
    val uriByCamera = remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
          viewModel.onImageSelected(uri)
        }
    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture()){
            viewModel.onImageSelected(uriByCamera.value)

        }

    val camPermission =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                uriByCamera.value = viewModel.createCameraImageFile(context)
                uriByCamera.value?.let { uri -> cameraLauncher.launch(uri)}
            } else {
                Toast.makeText(context, "Permission was not granted", Toast.LENGTH_SHORT)
                    .show()

            }
        }

    Scaffold (
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {},
               navigationIcon = {
                   IconButton(
                       onClick = {}
                   ) {
                       Icon(
                           imageVector = Icons.AutoMirrored.Default.ArrowBack,
                           contentDescription = null
                       )
                   }
               },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ){ innerpadding->
        Box(
            modifier = Modifier.padding(innerpadding),
            contentAlignment = Alignment.Center
        ) {

            Column {
                if (imageUri != null) {
                    Box(
                        modifier = Modifier
                            .aspectRatio(4/ 3f)
                            .padding(horizontal = 8.dp)

                    ){
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = null,
                            modifier = Modifier
                                .aspectRatio(16 / 9f)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )
                        IconButton(
                            onClick = { isSheetOpen = true },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .background(Color.Gray.copy(alpha = 0.6f), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Image",
                                tint = Color.White
                            )
                        }

                    }

                }
                else {
                    Card (
                        Modifier.clickable(
                            onClick = {
                                isSheetOpen = true
                            }
                        ).padding(horizontal = 16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer
                        )
                    ){
                        Box(
                            modifier = Modifier
                                .aspectRatio(4 / 3f)
                                .padding(horizontal = 16.dp)
                                .clip(
                                    RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center,
                            content = {

                                Column(
                                    modifier = Modifier,
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AddPhotoAlternate,
                                        contentDescription = "Add Image",
                                        modifier = Modifier.size(48.dp),
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        "Tap to select an image",
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        )
                    }


                }
                DropdownTextField(

                    label = "Meal Type",
                    onValueSelected = {},
                    options = listOf(MealType(mealType = "Breakfast"),MealType(mealType = "Brunch"))
                )
                TimePickerTextField(
                    onSelectedTime = {},
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                )
                DatePickerTextField(
                    onDateSelected = {},
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
                )
                DescriptionTextField(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    onValueChange = {},
                    value = "",
                    imeAction = ImeAction.Done,
                    readOnly = false
                )
                Button(
                    onClick = {/*Save Food entry*/},
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContentColor = MaterialTheme.colorScheme.surfaceBright,
                        disabledContainerColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Save"
                    )
                }
            }





        }
        if (isSheetOpen) {
            ModalBottomSheet(
                containerColor = MaterialTheme.colorScheme.surfaceBright,
                sheetState = bottomSheetState,
                onDismissRequest = { isSheetOpen = false }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Select Image Source",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Camera Option
                        IconButton(
                            onClick = {
                                camPermission.launch(android.Manifest.permission.CAMERA)
                                isSheetOpen = false
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.surfaceContainer)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CameraAlt,
                                contentDescription = "Camera",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        // Image Picker Option
//                        IconButton(
//                            onClick = {
////                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
////                                    storagePermission.launch(
////                                        android.Manifest.permission.READ_MEDIA_IMAGES
////                                    )
////                                } else {
////                                    storagePermission.launch(
////                                        android.Manifest.permission.READ_EXTERNAL_STORAGE
////                                    )
////                                    isSheetOpen = false
////                                }
//                                      },
//                            modifier = Modifier
//                                .clip(RoundedCornerShape(12.dp))
//                                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f))
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Photo,
//                                contentDescription = "Image Picker",
//                                tint = MaterialTheme.colorScheme.secondary
//                            )
//                        }
                    }
                }
            }
        }
        }

    }