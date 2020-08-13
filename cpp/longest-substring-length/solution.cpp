#include <string>
#include <iostream>
#include <bitset>

const int MAX_CHARS = 256;

int lenLongestSubstr(const std::string& s) {
	std::string::size_type len = s.size();
	std::bitset<MAX_CHARS> all_chars; // A bit for every character.
	int longest = 0;
	int i = 0;
	int j = 0;

	while (i < len && j < len) {
		if (all_chars[s[j]]) { // A character has repeated.
			// Reset the counters. The previous starting point must
			// be incremented by 1.
			all_chars[s[i++]] = false;
		} else {
			all_chars[s[j++]] = true;
			longest = std::max(longest, j - i);
		}
	}

	return longest;
}

void showResults(const std::string& s)
{
	std::cout << "Length of longest substring in " << s << " is "
	<< lenLongestSubstr(s) << std::endl;
}

int main(int argc, char* argv[], char* env[])
{
	showResults("absabcbb");
	showResults("bbbbbbb");
	showResults("pwwkew");

	return 0;
}

