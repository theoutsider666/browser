from sklearn.linear_model import LogisticRegression
import scanpy as sc
import pandas as pd
import numpy as np
import joblib
import argparse


#
def train_model(name):
    # data=sc.read_h5ad('./data/'+name+'.h5ad')
    data = sc.read_h5ad('C:\\Users\\MI\\Desktop\\bishe\\数据\\Bostaurus\\' + name + '.h5ad')
    # label=pd.read_csv('./data/'+name+'label.txt')['x']
    label = pd.read_csv('C:\\Users\\MI\\Desktop\\bishe\\数据\\Bostaurus\\' + name + '_label.txt')['x']
    data.var_names_make_unique(join='-')
    data.obs_names_make_unique(join='-')

    sc.pp.normalize_total(data)
    sc.pp.log1p(data)
    sc.pp.highly_variable_genes(data, n_top_genes=5000)
    data = data[:, data.var.highly_variable]

    model = LogisticRegression(C=0.1, random_state=11)
    model.fit(data.X, label)
    # joblib.dump(model, './data/'+name+'.model')
    joblib.dump(model, 'C:\\Users\\MI\\Desktop\\bishe\\数据\\Bostaurus\\model\\' + name + '.model')
    newdata = {
        'gene': np.array(data.var.index)
    }
    indexgene = pd.DataFrame(newdata, columns=None, index=None)
    indexgene.to_csv('C:\\Users\\MI\\Desktop\\bishe\\数据\\Bostaurus\\model\\' + name + 'geneindex.txt')


# train_model('PBMC')

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Parameters to be used for Stacking')
    parser.add_argument('-Species', '--species', required=True, help='input species name ')
    parser.add_argument('-Tissue', '--tissue', required=True, help='input tissue name')

# -filename
args = parser.parse_args()
species = args.species
tissue = args.tissue

# def predictmodel(species,tissue):
data = sc.read_h5ad('C:\\Users\\MI\\Desktop\\bishe\\upload\\' + tissue + '.h5ad')
data.var_names_make_unique(join='-')
data.obs_names_make_unique(join='-')
sc.pp.normalize_total(data)
sc.pp.log1p(data)
model = joblib.load('C:\\Users\\MI\\Desktop\\bishe\\数据\\{}\\model\\{}.model'.format(species, tissue))
gene = pd.read_csv('C:\\Users\\MI\\Desktop\\bishe\\数据\\{}\\model\\{}geneindex.txt'.format(species, tissue))['gene']
print(gene)
data = data[:, gene]
print(data)
model.predict(data.X)
print(model.predict(data.X))
predictions = model.predict(data.X)
with open('C:\\Users\\MI\\Desktop\\bishe\\test2_springboot\\myvue\\public\\predict\\{}-{}.txt'.format(species, tissue), 'w') as f:
    for p in predictions:
        f.write(str(p) + '\n')
# return

# dataname='./data/celseq2.h5ad'
# predictmodel(dataname,'celseq2')


# label = pd.read_csv('./data/celseq2label.txt')
#
#
# #
# sc.pp.normalize_total(data)
# sc.pp.log1p(data)
# # sc.pp.highly_variable_genes(data, n_top_genes=5000)
# # data = data[:, data.var.highly_variable]
# # a=data.var.highly_variable
# # a.to_csv('./data/celseq2.csv')
# #
# # model = LogisticRegression(C=0.1, random_state=11)
# # model.fit(data.X, label)
# # predict_classes = model.predict(data.X)
# # print(predict_classes)
# #
# # joblib.dump(model,'./data/celseq2.model')
#
#
#
# pdata = pd.read_csv('./data/celseq2.csv')['x']
# # data = data[:,pdata]
#
# # print("这是data.var"+data.var)
# print(data.var.index)
#
# model = joblib.load('./data/celseq2.model')
# model.predict(data.var.index)
#
#
#
