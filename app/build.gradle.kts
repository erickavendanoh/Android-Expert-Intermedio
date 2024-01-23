plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.erickavendanoh.horoscapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.erickavendanoh.horoscapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    //Para activar el ViewBinding
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    //Cuando se tienen varias librerias que ocupen la misma versión no es recomendable ponerlas manualmente (como se ve en algunos "implementation" de aquí donde la versión va después de los ":"), sino que se crean variables que contengan el valor de esas versiones y ya donde iría el numéro de la versión se pone la variable que corresponda...
    //...esto para evitar errores de que se le cambia a uno nada más y al otro ya no, y después haya problemas de compatibilidad y así. También por si se tienen que actualizar, ya solo cambiando el valor de la variable se actualice la versión en todas las que ocupen esa variable
    val navVersion = "2.7.1"

    //NavComponent
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //UnitTesting
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}