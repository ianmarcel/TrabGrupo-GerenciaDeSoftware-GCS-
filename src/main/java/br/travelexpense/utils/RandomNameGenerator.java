package br.travelexpense.utils;

import java.util.Random;

public class RandomNameGenerator {
    private static final String[] FIRST_NAMES = {
        "John", "Jane", "David", "Alice", "Michael", "Emily", "sdak", "sad", "Oliver", "Emma", "James", "Olivia",
        "William", "Ava", "Alexander", "Sophia", "Benjamin", "Isabella", "Henry", "Mia", "Daniel", "Charlotte",
        "Matthew", "Amelia", "Joseph", "Harper", "Samuel", "Evelyn", "Anthony", "Abigail", "Andrew", "Emily",
        "David", "Elizabeth", "Daniel", "Sofia", "Jackson", "Ella", "Henry", "Grace", "Michael", "Victoria",
        "Caleb", "Chloe", "Sebastian", "Scarlett", "Jack", "Aria", "Christopher", "Zoe", "Daniel", "Lily",
        "Wyatt", "Layla", "Gabriel", "Lillian", "Owen", "Nora", "Luke", "Hannah", "Isaac", "Lucy", "Nathan",
        "Zoey", "Ryan", "Penelope", "Elijah", "Aubrey", "Oliver", "Stella", "Leo", "Addison", "Aaron", "Bella",
        "Charles", "Natalie", "Thomas", "Luna", "Joshua", "Savannah", "Christian", "Maya", "Hunter", "Leah",
        "Connor", "Audrey", "Eli", "Aaliyah", "Evan", "Gabriella", "Samuel", "Claire", "Adrian", "Skylar",
        "Joseph", "Anna", "Julian", "Paisley", "Levi", "Ellie", "Isaiah", "Aurora", "Christopher", "Nevaeh"
    };

    private static final String[] LAST_NAMES = {
        "Smith", "Johnson", "Brown", "Lee", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas",
        "Jackson", "White", "Harris", "Clark", "Lewis", "Young", "Walker", "Hall", "King", "Baker", "Adams",
        "Allen", "Parker", "Wright", "Morris", "Cook", "Bailey", "Bell", "Cox", "Rivera", "Cooper", "Richardson",
        "Reed", "Russell", "Stewart", "Turner", "Morgan", "Kennedy", "Brooks", "Sanders", "Price", "Bennett",
        "Wood", "Watson", "Carter", "Gray", "James", "Foster", "Simmons", "Ortiz", "Hayes", "Mendoza", "Spencer",
        "Gomez", "Reyes", "Fisher", "Morales", "Kim", "Collins", "Sanders", "Ross", "Henderson", "Coleman",
        "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Barnes",
        "Cole", "Murphy", "Howard", "Rogers", "Gonzalez", "Reed", "Ramos", "Myers", "Kelly", "Price", "Brooks",
        "Scott", "Bailey", "Foster", "Rodriguez", "Alexander", "Myers", "Morgan", "Watkins", "Sullivan", "Wallace"
    };

    public String generateRandomName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        int min = 1; // Valor mínimo do intervalo
        int max = 1000000; // Valor máximo do intervalo

        // Gera um número aleatório entre min (inclusive) e max (exclusive)
        int randomNumber = random.nextInt(max - min) + min;
        return firstName + String.valueOf(randomNumber) + lastName;
    }

    public String generateRandomNumber() {
        Random random = new Random();
        int min = 1; // Valor mínimo do intervalo
        int max = 1000000; // Valor máximo do intervalo

        // Gera um número aleatório entre min (inclusive) e max (exclusive)
        int randomNumber1 = random.nextInt(max - min) + min;
        int randomNumber2 = random.nextInt(max - min) + min;
        int randomNumber3 = random.nextInt(max - min) + min;
        return String.valueOf(randomNumber3 + randomNumber1 + randomNumber2);
    }

}
