#include <iostream>
#include <string>
#include <vector>
#include <locale>
#include <codecvt>
#include <random>
#include <time.h>

int getTableSize(std::wstring);
std::vector<std::vector<wchar_t>> encrypt(int, std::wstring, std::wstring&, std::string&);
void decrypt(std::wstring&, std::string&);

int main() {
    std::string text;
    std::wstring wideText;
    std::vector<std::vector<wchar_t>> table;
    std::string cipher;
    std::wstring wideCipher;
    int tableSizeWidth;
    while(true) {
        srand(time(NULL));
        std::cout << "Enter the text to encrypt: ";
        std::getline(std::cin, text);
        std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> converter;
        wideText = converter.from_bytes(text);

        tableSizeWidth = getTableSize(wideText);
        table = encrypt(tableSizeWidth, wideText, wideCipher, cipher);
        decrypt(wideCipher, cipher);
        text.clear();
        wideText.clear();
        wideCipher.clear();
        cipher.clear();
    }
    return 0;
}

int getTableSize(std::wstring s) {
    int sLength = s.size();
    for(int i = sLength - 1; i > 1; i--) {
        if(sLength % i == 0) {
            //return i;
            float r = static_cast<float> (rand()) / static_cast<float> (RAND_MAX);
            if(r > 0.5) return sLength / i;
        }
    }
    return 0;
}

std::vector<std::vector<wchar_t>> encrypt(int tableSizeWidth, std::wstring wideText,
                                        std::wstring &wideCipher, std::string &cipher) {

    std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> converter;
    std::vector<std::vector<wchar_t>> table;
    if(tableSizeWidth == 0) {
        std::cout << "Incorrect text length.\n";
    } else {
        std::vector<wchar_t> t;
        int currIndex = 0;
        for(int i = 0; i < wideText.size(); i++) {
            t.push_back(wideText[i]);
            currIndex++;
            if(currIndex != 0 && currIndex % tableSizeWidth == 0) {
                table.push_back(t);
                t.clear();
            }
        }
    }

    for(int i = 0; i < tableSizeWidth; i++) {
        for(int j = 0; j < table.size(); j++) {
            wideCipher+=table[j][i];
        }
    }
    cipher = converter.to_bytes(wideCipher);
    std::cout << "Cipher is: " << cipher << std::endl;
    return table;
}

void decrypt(std::wstring& wideCipher, std::string& cipher) {
    std::wstring_convert<std::codecvt_utf8_utf16<wchar_t>> converter;
    std::wstring wideAnswer;
    std::string answer;
    int cipherSize = wideCipher.size();
    int tWidth;
    for(int i = 2; i < cipherSize; i++) {
        tWidth = i;
        if(cipherSize % i == 0) {
            int startIndex = 0;
            for(int j = startIndex; j < cipherSize; j += tWidth) {
                wideAnswer+=wideCipher[j];
                if(j + tWidth >= cipherSize) {
                    j = startIndex + 1;
                    startIndex++;
                    j -= tWidth;
                    if(startIndex == tWidth) {
                        break;
                    }
                }
            }
            answer = converter.to_bytes(wideAnswer);
            std::cout << answer << std::endl;
            wideAnswer.clear();
        }
    }
}