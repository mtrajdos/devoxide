from urllib.request import urlopen

def read_url_into_list(url):
    with urlopen(url) as stories:
        story_words = []
        for line in stories:
            line_words = line.decode('utf-8').split()
            for word in line_words:
                story_words.append(word)
    return story_words

feed_url = 'http://feeds.skynews.com/feeds/rss/uk.xml'
all_words = read_url_into_list(feed_url)

print(all_words)

if 'Hancock' in all_words:
    print('Found Hancock')
else:
    print('Not found')