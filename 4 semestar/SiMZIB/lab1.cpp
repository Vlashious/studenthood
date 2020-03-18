#include <iostream>
#include <random>
#include <string>
#include <cmath>
#include <vector>
#include <locale>
#include <codecvt>
#include <chrono>

int main() {

    srand(time(NULL));

    int length = 0;
    std::string passType;
    std::vector<int> passTypeInt;
    std::wstring password;
    std::vector<std::wstring> alfabet = {
        {L"1234567890"},
        {L"ABCDEFGHIJKLMNOPQRSTUVWXYZ"},
        {L"abcdefghijklmnopqrstuvwxyz"},
        {L"АБВГҐДЕЁЖЗІЙКЛМНОПРСТУЎФХЦЧШЫЬЭЯЮ"},
        {L"абвгґдеёжзійклмнопрстуфхцчшыьэюя"}
    };
while(true) {
    std::cout << "Parol jakoha typu zheneravać?\n 0 - dadać ličby\n" <<
                " 1 - dadać vialikija litary (eng)\n 2 - dadać maleńkija litary (eng)\n" <<
                " 3 - dadać vialikija litary (biel)\n 4 - dadać maleńkija litary (biel)\n";
    std::cin >> passType;
    for(int i = 0; i < passType.size(); i++) {
        passTypeInt.push_back(passType[i] - '0');
    }

    std::cout << "Uviedziacie daŭžyniu parolu: ";
    std::cin >> length;

    for(int i = 0; i < length; i++) {
        int type = rand() % passTypeInt.size();
        type = passTypeInt[type];
        int pos = rand() % alfabet[type].size();
        password.insert(password.size(), 1, alfabet[type][pos]);
    }
    std::cout << "Vaš parol: ";
    std::wstring_convert<std::codecvt_utf8<wchar_t>, wchar_t> utf8_conv;
    std::string utf8string;
    for(auto &ch : password) utf8string.append(utf8_conv.to_bytes(ch));
    std::cout << utf8string << std::endl;

    //uzlom

    srand(time(NULL));
    std::wstring passwordBreak;
    auto start = std::chrono::high_resolution_clock::now();
    auto stop = std::chrono::high_resolution_clock::now();
    while(1) {
        for(int i = 0; i < length; i++) {
            int type = rand() % passTypeInt.size();
            type = passTypeInt[type];
            int pos = rand() % alfabet[type].size();
            passwordBreak.insert(passwordBreak.size(), 1, alfabet[type][pos]);
        }
        if (password == passwordBreak) {
            stop = std::chrono::high_resolution_clock::now();
            break;
        }
        passwordBreak = L"";
    }

    auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(stop - start);

    std::cout << "Čas, zatračany na ŭzłom: " << duration.count() << std::endl;
    password = L"";
}
    return 0;
}