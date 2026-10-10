package com.example.data

enum class Difficulty(val label: String, val icon: String, val pointsPerWord: Int) {
    BEGINNER("Beginner Bee", "🌱", 100),
    INTERMEDIATE("Honey Worker", "🐝", 150),
    ADVANCED("Queen Bee", "👑", 200),
    MIXED("All-Bee Mix", "🍯", 125)
}

data class SpellingWord(
    val word: String,
    val phonetic: String,
    val definition: String,
    val exampleSentence: String,
    val difficulty: Difficulty,
    val options: List<String>,
    val mnemonicTip: String,
    val maskedWord: String,
    val correctMissingLetters: String,
    val missingLetterOptions: List<String>
)

object WordBank {

    val allWords: List<SpellingWord> = listOf(
        // ==================== BEGINNER ====================
        SpellingWord(
            word = "friend",
            phonetic = "/frend/",
            definition = "A person with whom one has a bond of mutual affection.",
            exampleSentence = "A true friend is always there to help you.",
            difficulty = Difficulty.BEGINNER,
            options = listOf("friend", "freind", "frend", "friende"),
            mnemonicTip = "Remember: A friend is with you to the 'end'!",
            maskedWord = "FR__ND",
            correctMissingLetters = "IE",
            missingLetterOptions = listOf("IE", "EI", "E", "EA")
        ),
        SpellingWord(
            word = "because",
            phonetic = "/bɪˈkɒz/",
            definition = "For the reason that; since.",
            exampleSentence = "We stayed inside because it began to rain.",
            difficulty = Difficulty.BEGINNER,
            options = listOf("because", "becuase", "becasue", "because"),
            mnemonicTip = "Big Elephants Can Always Understand Small Elephants (B-E-C-A-U-S-E)!",
            maskedWord = "B__AUSE",
            correctMissingLetters = "EC",
            missingLetterOptions = listOf("EC", "EK", "AK", "IC")
        ),
        SpellingWord(
            word = "believe",
            phonetic = "/bɪˈliːv/",
            definition = "To accept something as true or feel sure of the truth.",
            exampleSentence = "I believe you can win the spelling bee!",
            difficulty = Difficulty.BEGINNER,
            options = listOf("believe", "beleive", "belive", "beleeve"),
            mnemonicTip = "Don't believe a 'lie' — notice the word 'lie' right in the middle!",
            maskedWord = "BEL__VE",
            correctMissingLetters = "IE",
            missingLetterOptions = listOf("IE", "EI", "EE", "Y")
        ),
        SpellingWord(
            word = "weather",
            phonetic = "/ˈweð.ər/",
            definition = "The state of the atmosphere with respect to wind, temperature, and rain.",
            exampleSentence = "The weather was sunny and warm during our picnic.",
            difficulty = Difficulty.BEGINNER,
            options = listOf("weather", "whether", "wether", "waether"),
            mnemonicTip = "We 'eat' when there's bad w-eat-her!",
            maskedWord = "W__THER",
            correctMissingLetters = "EA",
            missingLetterOptions = listOf("EA", "E", "EE", "AE")
        ),
        SpellingWord(
            word = "puzzle",
            phonetic = "/ˈpʌz.əl/",
            definition = "A game, toy, or problem designed to test ingenuity or knowledge.",
            exampleSentence = "She solved the crossword puzzle in ten minutes.",
            difficulty = Difficulty.BEGINNER,
            options = listOf("puzzle", "puzzel", "puzle", "puszle"),
            mnemonicTip = "Double 'z' like the sound of a sleeping bee: z-z-z!",
            maskedWord = "PU__LE",
            correctMissingLetters = "ZZ",
            missingLetterOptions = listOf("ZZ", "Z", "SS", "SZ")
        ),
        SpellingWord(
            word = "yellow",
            phonetic = "/ˈjel.oʊ/",
            definition = "The color of ripe lemons or sunflowers.",
            exampleSentence = "The bumblebee flew towards the bright yellow flower.",
            difficulty = Difficulty.BEGINNER,
            options = listOf("yellow", "yelow", "yelllow", "yallow"),
            mnemonicTip = "Two 'l's standing tall like sunflower stems!",
            maskedWord = "YE__OW",
            correctMissingLetters = "LL",
            missingLetterOptions = listOf("LL", "L", "W", "RL")
        ),
        SpellingWord(
            word = "garden",
            phonetic = "/ˈɡɑːr.dən/",
            definition = "A piece of ground adjoining a house, used for growing flowers, fruit, or vegetables.",
            exampleSentence = "Bees love pollinating the flowers in our backyard garden.",
            difficulty = Difficulty.BEGINNER,
            options = listOf("garden", "gardon", "gardin", "gaurden"),
            mnemonicTip = "Ends with 'den' like where a happy animal rests!",
            maskedWord = "GAR__N",
            correctMissingLetters = "DE",
            missingLetterOptions = listOf("DE", "DA", "DO", "DI")
        ),
        SpellingWord(
            word = "listen",
            phonetic = "/ˈlɪs.ən/",
            definition = "To give one's attention to a sound.",
            exampleSentence = "Please listen carefully to the speaker's voice.",
            difficulty = Difficulty.BEGINNER,
            options = listOf("listen", "listin", "lisen", "liston"),
            mnemonicTip = "Notice the silent 't' — 'listen' has the same letters as 'silent'!",
            maskedWord = "LIS__N",
            correctMissingLetters = "TE",
            missingLetterOptions = listOf("TE", "TI", "SE", "E")
        ),
        SpellingWord(
            word = "rabbit",
            phonetic = "/ˈræb.ɪt/",
            definition = "A burrowing, gregarious, plant-eating mammal with long ears.",
            exampleSentence = "A white rabbit hopped through the clover field.",
            difficulty = Difficulty.BEGINNER,
            options = listOf("rabbit", "rabit", "rabbitt", "rabbet"),
            mnemonicTip = "Two 'b's look like two bunny ears perked up!",
            maskedWord = "RA__IT",
            correctMissingLetters = "BB",
            missingLetterOptions = listOf("BB", "B", "PP", "TT")
        ),
        SpellingWord(
            word = "castle",
            phonetic = "/ˈkɑː.səl/",
            definition = "A large building, typically of the medieval period, fortified against attack.",
            exampleSentence = "The stone castle overlooked the green valley.",
            difficulty = Difficulty.BEGINNER,
            options = listOf("castle", "castel", "cassel", "casle"),
            mnemonicTip = "Contains a silent 't' just like whistle and thistle!",
            maskedWord = "CAS__E",
            correctMissingLetters = "TL",
            missingLetterOptions = listOf("TL", "SL", "L", "EL")
        ),
        SpellingWord(
            word = "guitar",
            phonetic = "/ɡɪˈtɑːr/",
            definition = "A stringed musical instrument played with the fingers or a plectrum.",
            exampleSentence = "He played a cheerful melody on his acoustic guitar.",
            difficulty = Difficulty.BEGINNER,
            options = listOf("guitar", "gitar", "guiter", "guittar"),
            mnemonicTip = "Begins with 'gui' just like 'guide'!",
            maskedWord = "G__TAR",
            correctMissingLetters = "UI",
            missingLetterOptions = listOf("UI", "I", "U", "EE")
        ),
        SpellingWord(
            word = "planet",
            phonetic = "/ˈplæn.ɪt/",
            definition = "A celestial body moving in an elliptical orbit around a star.",
            exampleSentence = "Earth is our beautiful home planet.",
            difficulty = Difficulty.BEGINNER,
            options = listOf("planet", "plannet", "planit", "planett"),
            mnemonicTip = "Only one 'n' in the plan of a planet!",
            maskedWord = "PLA__T",
            correctMissingLetters = "NE",
            missingLetterOptions = listOf("NE", "NI", "NNE", "NET")
        ),

        // ==================== INTERMEDIATE ====================
        SpellingWord(
            word = "accommodate",
            phonetic = "/əˈkɑː.mə.deɪt/",
            definition = "To provide sufficient space or lodging for someone.",
            exampleSentence = "The auditorium can accommodate up to one thousand guests.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("accommodate", "accomodate", "acommodate", "acomodate"),
            mnemonicTip = "Two C's and two M's — lots of space for two Cots and two Mattresses!",
            maskedWord = "A__O__ODATE",
            correctMissingLetters = "CC-MM",
            missingLetterOptions = listOf("CC-MM", "C-MM", "CC-M", "C-M")
        ),
        SpellingWord(
            word = "necessary",
            phonetic = "/ˈnes.ə.ser.i/",
            definition = "Required to be done, achieved, or present; essential.",
            exampleSentence = "Proper rest is necessary for a sharp mind.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("necessary", "neccessary", "necesary", "necessery"),
            mnemonicTip = "Never Forget: A shirt has 1 Collar (one 'c') and 2 Sleeves (two 's's)!",
            maskedWord = "NE__E__ARY",
            correctMissingLetters = "C-SS",
            missingLetterOptions = listOf("C-SS", "CC-SS", "C-S", "CC-S")
        ),
        SpellingWord(
            word = "separate",
            phonetic = "/ˈsep.ər.ət/",
            definition = "Forming or viewed as a unit by itself; distinct.",
            exampleSentence = "Keep the colored pencils in separate containers.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("separate", "seperate", "seprate", "seperite"),
            mnemonicTip = "There is 'A RAT' in sep-A-RAT-e!",
            maskedWord = "SEP__ATE",
            correctMissingLetters = "AR",
            missingLetterOptions = listOf("AR", "ER", "OR", "UR")
        ),
        SpellingWord(
            word = "calendar",
            phonetic = "/ˈkæl.ən.dər/",
            definition = "A chart or pages showing the days, weeks, and months of a particular year.",
            exampleSentence = "Mark the national spelling bee date on your wall calendar.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("calendar", "calender", "calandar", "celendar"),
            mnemonicTip = "It ends in 'ar' — keep an eye on each day of the ye-AR!",
            maskedWord = "CALEN__",
            correctMissingLetters = "DAR",
            missingLetterOptions = listOf("DAR", "DER", "DIR", "DOR")
        ),
        SpellingWord(
            word = "definitely",
            phonetic = "/ˈdef.ɪ.nət.li/",
            definition = "Without doubt; in a clear and definite manner.",
            exampleSentence = "We will definitely visit the botanical gardens today.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("definitely", "definately", "definetly", "defanitely"),
            mnemonicTip = "Root word is 'finite' — d-e-FINITE-l-y! Never an 'a'!",
            maskedWord = "DEFIN__ELY",
            correctMissingLetters = "IT",
            missingLetterOptions = listOf("IT", "AT", "ET", "T")
        ),
        SpellingWord(
            word = "embarrass",
            phonetic = "/ɪmˈbær.əs/",
            definition = "To cause someone to feel awkward, self-conscious, or ashamed.",
            exampleSentence = "Making a spelling mistake should never embarrass you; it helps you learn!",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("embarrass", "embarass", "emberrass", "embaras"),
            mnemonicTip = "I go Really Red And Smile Shyly — two R's and two S's!",
            maskedWord = "EMBA__A__",
            correctMissingLetters = "RR-SS",
            missingLetterOptions = listOf("RR-SS", "R-SS", "RR-S", "R-S")
        ),
        SpellingWord(
            word = "rhythm",
            phonetic = "/ˈrɪð.əm/",
            definition = "A strong, regular, repeated pattern of movement or sound.",
            exampleSentence = "The bumblebee danced to the steady rhythm of the song.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("rhythm", "rythm", "rhthym", "rhythem"),
            mnemonicTip = "Rhythm Helps Your Two Hips Move (R-H-Y-T-H-M)!",
            maskedWord = "R__THM",
            correctMissingLetters = "HY",
            missingLetterOptions = listOf("HY", "Y", "HI", "I")
        ),
        SpellingWord(
            word = "receipt",
            phonetic = "/rɪˈsiːt/",
            definition = "A written acknowledgment that a specified article or sum has been received.",
            exampleSentence = "Always keep the purchase receipt for your books.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("receipt", "reciept", "reciet", "reseipt"),
            mnemonicTip = "'I before E except after C' — hence 'ei' following 'c' with a silent 'p'!",
            maskedWord = "REC__PT",
            correctMissingLetters = "EI",
            missingLetterOptions = listOf("EI", "IE", "EE", "EA")
        ),
        SpellingWord(
            word = "vacuum",
            phonetic = "/ˈvæk.juːm/",
            definition = "A space entirely devoid of matter, or an electrical cleaner.",
            exampleSentence = "A sound wave cannot travel through an absolute vacuum.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("vacuum", "vaccuum", "vacume", "vacum"),
            mnemonicTip = "One 'c' and double 'u' — one of the very few English words with two U's!",
            maskedWord = "VAC__M",
            correctMissingLetters = "UU",
            missingLetterOptions = listOf("UU", "U", "CU", "OU")
        ),
        SpellingWord(
            word = "guarantee",
            phonetic = "/ˌɡær.ənˈtiː/",
            definition = "A formal pledge that a certain condition will be fulfilled.",
            exampleSentence = "Practice and dedication guarantee strong vocabulary growth.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("guarantee", "garantee", "garentee", "guarente"),
            mnemonicTip = "Begins like a 'guard' (G-U-A-R) and ends with double 'e'!",
            maskedWord = "G__RANTEE",
            correctMissingLetters = "UA",
            missingLetterOptions = listOf("UA", "A", "AU", "E")
        ),
        SpellingWord(
            word = "schedule",
            phonetic = "/ˈskedʒ.uːl/",
            definition = "A plan for carrying out a process or procedure, giving lists of intended times.",
            exampleSentence = "Stick to your daily study schedule to reach your goals.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("schedule", "schedual", "shedule", "skedule"),
            mnemonicTip = "Starts with 'SCH' like 'school'!",
            maskedWord = "S__EDULE",
            correctMissingLetters = "CH",
            missingLetterOptions = listOf("CH", "K", "CK", "H")
        ),
        SpellingWord(
            word = "foreign",
            phonetic = "/ˈfɔːr.ən/",
            definition = "Characteristic of, or situated outside one's own country.",
            exampleSentence = "She loves learning words from foreign languages.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("foreign", "foriegn", "foren", "forein"),
            mnemonicTip = "Ends like 'reign' — a foreign king reigns!",
            maskedWord = "FOR__GN",
            correctMissingLetters = "EI",
            missingLetterOptions = listOf("EI", "IE", "EE", "A")
        ),
        SpellingWord(
            word = "knowledge",
            phonetic = "/ˈnɑː.lɪdʒ/",
            definition = "Facts, information, and skills acquired through experience or education.",
            exampleSentence = "Reading books expands your spelling knowledge.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("knowledge", "knowlege", "nowledge", "knowledg"),
            mnemonicTip = "It's all about what you KNOW plus the LEDGE to stand on!",
            maskedWord = "KNOW__GE",
            correctMissingLetters = "LED",
            missingLetterOptions = listOf("LED", "LE", "LID", "L")
        ),
        SpellingWord(
            word = "parallel",
            phonetic = "/ˈpær.ə.lel/",
            definition = "Side by side and having the same distance continuously between them.",
            exampleSentence = "Train tracks run parallel to one another.",
            difficulty = Difficulty.INTERMEDIATE,
            options = listOf("parallel", "paralell", "parrallel", "paralel"),
            mnemonicTip = "The two 'l's in the middle stand parallel to each other!",
            maskedWord = "PARA__EL",
            correctMissingLetters = "LL",
            missingLetterOptions = listOf("LL", "L", "RR", "TT")
        ),

        // ==================== ADVANCED ====================
        SpellingWord(
            word = "onomatopoeia",
            phonetic = "/ˌɑː.nəˌmæt̬.əˈpiː.ə/",
            definition = "The naming of a thing or action by a vocal imitation of the sound associated with it.",
            exampleSentence = "'Buzz' and 'hiss' are classic examples of onomatopoeia.",
            difficulty = Difficulty.ADVANCED,
            options = listOf("onomatopoeia", "onomatopia", "onomatopeia", "onomatapoeia"),
            mnemonicTip = "Notice the Greek diphthong: -P-O-E-I-A at the ending!",
            maskedWord = "ONOMATO__IA",
            correctMissingLetters = "POE",
            missingLetterOptions = listOf("POE", "PI", "PE", "PAE")
        ),
        SpellingWord(
            word = "conscientious",
            phonetic = "/ˌkɑːn.ʃiˈen.ʃəs/",
            definition = "Wishing to do one's work or duty well and thoroughly.",
            exampleSentence = "She is a conscientious student who double-checks every word.",
            difficulty = Difficulty.ADVANCED,
            options = listOf("conscientious", "consciencious", "consientious", "conscientous"),
            mnemonicTip = "Starts with 'science' (CON-SCIENCE-tious) — being mindful of knowledge!",
            maskedWord = "CON__IENTIOUS",
            correctMissingLetters = "SC",
            missingLetterOptions = listOf("SC", "S", "C", "XC")
        ),
        SpellingWord(
            word = "miscellaneous",
            phonetic = "/ˌmɪs.əˈleɪ.ni.əs/",
            definition = "Consisting of a variety of things; diverse and unclassified.",
            exampleSentence = "The bee collected pollen from miscellaneous wildflowers.",
            difficulty = Difficulty.ADVANCED,
            options = listOf("miscellaneous", "miscellanious", "miscelaneous", "misscellaneous"),
            mnemonicTip = "Single 's', double 'l', followed by -aneous!",
            maskedWord = "MISCE__ANEOUS",
            correctMissingLetters = "LL",
            missingLetterOptions = listOf("LL", "L", "RR", "SS")
        ),
        SpellingWord(
            word = "bureaucracy",
            phonetic = "/bjʊˈrɑː.krə.si/",
            definition = "A system of government or administration marked by excessive paperwork and rigid rules.",
            exampleSentence = "Reforms were initiated to simplify complex school bureaucracy.",
            difficulty = Difficulty.ADVANCED,
            options = listOf("bureaucracy", "beuraucracy", "bureacracy", "bureaucrasy"),
            mnemonicTip = "Begins with the French word for desk: B-U-R-E-A-U!",
            maskedWord = "B__EAUCRACY",
            correctMissingLetters = "UR",
            missingLetterOptions = listOf("UR", "EUR", "AR", "ER")
        ),
        SpellingWord(
            word = "fluorescent",
            phonetic = "/flɔːˈres.ənt/",
            definition = "Giving off vivid light through fluorescence; bright and glowing.",
            exampleSentence = "The classroom was lit with bright fluorescent bulbs.",
            difficulty = Difficulty.ADVANCED,
            options = listOf("fluorescent", "flourescent", "florescent", "fluoresent"),
            mnemonicTip = "'FLU' like the river flows, not FLO: F-L-U-O-R-E-S-C-E-N-T!",
            maskedWord = "FL__RESCENT",
            correctMissingLetters = "UO",
            missingLetterOptions = listOf("UO", "OU", "O", "EW")
        ),
        SpellingWord(
            word = "lieutenant",
            phonetic = "/luːˈten.ənt/",
            definition = "A deputy or substitute acting for a superior; an officer rank.",
            exampleSentence = "The lieutenant carried out the orders with precision.",
            difficulty = Difficulty.ADVANCED,
            options = listOf("lieutenant", "leutenant", "lieutenent", "lieutennant"),
            mnemonicTip = "Three vowels in a row at the front: L-I-E-U (Lie in Europe under ten ants)!",
            maskedWord = "L__UTENANT",
            correctMissingLetters = "IE",
            missingLetterOptions = listOf("IE", "E", "EI", "EU")
        ),
        SpellingWord(
            word = "surveillance",
            phonetic = "/sɚˈveɪ.ləns/",
            definition = "Close observation, especially of a suspected spy or criminal.",
            exampleSentence = "Camera surveillance helped protect the botanical research center.",
            difficulty = Difficulty.ADVANCED,
            options = listOf("surveillance", "surveilance", "survalience", "surveillence"),
            mnemonicTip = "Double 'l' and ends with -ANCE, not -ence!",
            maskedWord = "SURVEI__ANCE",
            correctMissingLetters = "LL",
            missingLetterOptions = listOf("LL", "L", "RR", "TT")
        ),
        SpellingWord(
            word = "chrysanthemum",
            phonetic = "/krɪˈsæn.θə.məm/",
            definition = "A popular autumn garden plant of the daisy family, with brightly colored flowers.",
            exampleSentence = "The golden chrysanthemum attracted bees from all around.",
            difficulty = Difficulty.ADVANCED,
            options = listOf("chrysanthemum", "chrysanthimum", "crysanthemum", "chrysanthemam"),
            mnemonicTip = "Begins with 'CHRY' like Greek for gold (chrysos)!",
            maskedWord = "CHR__ANTHEMUM",
            correctMissingLetters = "YS",
            missingLetterOptions = listOf("YS", "IS", "Y", "ES")
        ),
        SpellingWord(
            word = "silhouette",
            phonetic = "/ˌsɪl.uˈet/",
            definition = "The dark shape and outline of someone or something visible against a lighter background.",
            exampleSentence = "We saw the silhouette of a bee against the setting sun.",
            difficulty = Difficulty.ADVANCED,
            options = listOf("silhouette", "silhouete", "silouette", "silhouett"),
            mnemonicTip = "From French: S-I-L-H-O-U-E-T-T-E with 'ou' and double 'tt'!",
            maskedWord = "SILH__ETTE",
            correctMissingLetters = "OU",
            missingLetterOptions = listOf("OU", "U", "OW", "O")
        ),
        SpellingWord(
            word = "pharaoh",
            phonetic = "/ˈfer.oʊ/",
            definition = "A ruler in ancient Egypt.",
            exampleSentence = "The golden mask of the pharaoh was displayed in the museum.",
            difficulty = Difficulty.ADVANCED,
            options = listOf("pharaoh", "pharoh", "pharoah", "faroh"),
            mnemonicTip = "Watch the ending: A-O-H, not O-A-H!",
            maskedWord = "PHAR__",
            correctMissingLetters = "AOH",
            missingLetterOptions = listOf("AOH", "OAH", "OH", "AH")
        )
    )

    fun getWordsByDifficulty(difficulty: Difficulty): List<SpellingWord> {
        return when (difficulty) {
            Difficulty.MIXED -> allWords.shuffled()
            else -> allWords.filter { it.difficulty == difficulty }.shuffled()
        }
    }
}
