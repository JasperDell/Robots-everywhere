import java.util.List;
import java.util.ArrayList;

public class Club {
int totalMoneySpend = 0;
int numberOfPeople = 0;
int numberOfPeopleDancing = 0;
int musicVolume = 0;
List<Person> crowd;

void Init () {
    crowd = new ArrayList<>();
}

void setTotalMoneySpend(int x){
    totalMoneySpend = x;
}
void updateTotalMoneySpend(int x){
    totalMoneySpend+=x;
}
void setNumberOfPeople(int x){
    numberOfPeople = x;
}
void updateNumberOfPeople(int x){
    numberOfPeople+=x;
}
void setNumberOfPeopleDancing(int x){
    numberOfPeopleDancing = x;
}
void updateNumberOfPeopleDancing(int x){
    numberOfPeopleDancing+=x;
}
void setMusicVolume(int x){
    musicVolume = x;
}
void updateMusicVolume(int x){
    musicVolume+=x;
}
}
