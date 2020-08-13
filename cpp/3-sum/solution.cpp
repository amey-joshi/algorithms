#include <iostream>
#include <algorithm>
#include <vector>
#include <set>
#include <tuple>
#include <chrono>

struct Triple {
	int l, m, n;
	Triple(int x, int y, int z) : l(x), m(y), n(z) {};
	Triple(const Triple& t) {
		l = t.l;
		m = t.m;
		n = t.n;
	}

	bool operator==(const Triple &rhs) const {
		return l == rhs.l && m == rhs.m && n == rhs.n;
	}

	bool operator <(const Triple& rhs) const {
		return std::tie(l, m, n) < std::tie(rhs.l, rhs.m, rhs.n);
	}

	std::vector<int> asVector() const {
		std::vector<int> v;
		v.push_back(l);
		v.push_back(m);
		v.push_back(n);

		return v;
	}
};

std::vector<std::vector<int>> threeSum(std::vector<int> &nums)
{
	std::vector<std::vector<int>> results;
	if (nums.size() < 3) return results;

	int store[] = {0, 1, 2}; // Lexicographically lowest combination.
	std::set<Triple> s;

	std::sort(nums.begin(), nums.end());
	auto t0 = std::chrono::steady_clock::now();
	int args[] = {0, 0, 0};
	int temp;

	for (int i = 0; i < nums.size() - 2; ++i) {
		int a = nums[i];
		int l = i + 1;
		int r = nums.size() - 1;

		while (l < r) {
			int b = nums[l];
			int c = nums[r];

			if (a + b + c == 0) {
				s.insert(Triple(a, b, c));
				--r;
				++l;
			} else if (a + b + c > 0) {
				--r;
			} else {
				++l;
			}
		}
	}

	auto t1 = std::chrono::steady_clock::now();
	auto diff = t1 - t0;
	std::cout << "Checking all combinations tooks " << 
	std::chrono::duration<double, std::milli> (diff).count() << 
	" ms." << std::endl;

	for (auto&t: s) {
		std::vector<int> v = t.asVector();
		results.push_back(v);
	} 
	auto t2 = std::chrono::steady_clock::now();

	diff = t2 - t1;
	std::cout << "Packaging all combinations tooks " << 
	std::chrono::duration<double, std::milli> (diff).count() << 
	" ms." << std::endl;

	return results;
}

int main(int argc, char* argv[], char* envp[])
{
	std::vector<int> v{-1, 0, 1, 3, -1, -4};
	std::vector<std::vector<int>> results = threeSum(v);

	for (auto& t: results) {
		for (int n: t) {
			std::cout << n << ", ";
		}
		std::cout << std::endl;
	}

	return 0;
}


