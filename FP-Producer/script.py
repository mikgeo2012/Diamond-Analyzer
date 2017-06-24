import csv

data = [
["GIA", "G", "VS1", "Excellent", "0.7", "2520"],
    ["GIA", "G", "VS1", "Excellent", "1.04", "8620"],
    ["GIA", "G", "VS1", "Excellent", "0.9", "5540"],
    ["GIA", "G", "VS1", "Excellent", "1.27", "9580"],
    ["GIA", "G", "VS1", "Excellent", "1.25", "10180"],
    ["GIA", "G", "VS1", "Excellent", "0.78", "3710"],
    ["GIA", "G", "VS1", "Excellent", "1.32", "10310"],
    ["GIA", "G", "VS1", "Excellent", "0.75", "3650"],
    ["GIA", "G", "VS1", "Excellent", "0.72", "3590"],
    ["GIA", "G", "VS1", "Excellent", "1.2", "7300"],
    ["GIA", "G", "VS1", "Excellent", "1.3", "9530"],
    ["GIA", "G", "VS1", "Excellent", "1.28", "9830"],
    ["GIA", "G", "VS1", "Excellent", "0.9", "5140"],
    ["GIA", "G", "VS1", "Excellent", "0.93", "5"],
    ["GIA", "G", "VS1", "Excellent", "1", "6010"],
    ["GIA", "H", "VVS2", "Excellent", "0.71", "3330"],
    ["GIA", "H", "VVS2", "Excellent", "1.06", "6620"],
    ["GIA", "H", "VVS2", "Excellent", "1.5", "11940"],
    ["GIA", "H", "VVS2", "Excellent", "1.32", "9350"],
    ["GIA", "H", "VVS2", "Excellent", "1.09", "7200"],
    ["GIA", "H", "VVS2", "Excellent", "1.04", "7280"],
    ["GIA", "H", "VVS2", "Excellent", "1", "6660"],
    ["GIA", "H", "VVS2", "Excellent", "1.24", "7000"],
    ["GIA", "H", "VVS2", "Excellent", "1.2", "8350"],
    ["GIA", "H", "VVS2", "Excellent", "1.16", "8220"],
    ["GIA", "H", "VVS2", "Excellent", "1.11", "6260"],
    ["GIA", "H", "VVS2", "Excellent", "1.2", "9220"],
    ["GIA", "H", "VVS2", "Excellent", "1.05", "6940"],
    ["GIA", "H", "VVS2", "Excellent", "0.96", "5810"],
    ["GIA", "H", "VVS2", "Excellent", "1.41", "9740"],
    ["GIA", "I", "VS2", "Excellent", "0.7", "2440"],
    ["GIA", "I", "VS2", "Excellent", "0.7", "3380"],
    ["GIA", "I", "VS2", "Excellent", "0.71", "3790"],
    ["GIA", "I", "VS2", "Excellent", "0.78", "3580"],
    ["GIA", "I", "VS2", "Excellent", "1.5", "12110"],
    ["GIA", "I", "VS2", "Excellent", "1.2", "8920"],
    ["GIA", "I", "VS2", "Excellent", "1.13", "7700"],
    ["GIA", "I", "VS2", "Excellent", "1", "7610"],
    ["GIA", "I", "VS2", "Excellent", "0.9", "4200"],
    ["GIA", "I", "VS2", "Excellent", "0.9", "5050"],
    ["GIA", "I", "VS2", "Excellent", "0.8", "4180"],
    ["GIA", "I", "VS2", "Excellent", "1.5", "13200"],
    ["GIA", "I", "VS2", "Excellent", "1.2", "9720"],
    ["GIA", "I", "VS2", "Excellent", "0.95", "5400"],
    ["GIA", "I", "VS2", "Excellent", "0.75", "3550"],
    ["AGS", "J", "VVS1", "Ideal", "0.7", "1820"],
    ["AGS", "J", "VVS1", "Ideal", "0.72", "2050"],
    ["AGS", "J", "VVS1", "Ideal", "0.81", "2460"],
    ["AGS", "J", "VVS1", "Ideal", "1.18", "8910"],
    ["AGS", "J", "VVS1", "Ideal", "1.4", "6370"],
    ["AGS", "J", "VVS1", "Ideal", "1.5", "8450"],
    ["AGS", "J", "VVS1", "Ideal", "0.82", "2870"],
    ["AGS", "J", "VVS1", "Ideal", "0.9", "4200"],
    ["AGS", "J", "VVS1", "Ideal", "0.93", "4060"],
    ["AGS", "J", "VVS1", "Ideal", "0.91", "3770"],
    ["AGS", "J", "VVS1", "Ideal", "0.77", "2940"],
    ["AGS", "J", "VVS1", "Ideal", "0.75", "2320"],
    ["AGS", "J", "VVS1", "Ideal", "1.16", "5650"],
    ["AGS", "J", "VVS1", "Ideal", "1.17", "5120"],
    ["AGS", "J", "VVS1", "Ideal", "1.18", "5580"]

  
  

]


def write():
    for i in range(0, 60):
        filename = "a" + str(i) + ".csv"
        with open(filename, 'wb') as myfile:
            wr = csv.writer(myfile, quoting=csv.QUOTE_ALL)
            wr.writerow(data[i])

write()

